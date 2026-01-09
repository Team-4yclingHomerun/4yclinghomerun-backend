import ws from 'k6/ws'; // 웹소켓 연결 메시지 송수신
import {sleep, check} from 'k6'; // 테스트 검증 함수
import {Counter, Trend} from 'k6/metrics'; // 커스텀 메트릭 오류 카운트 확인용



// ----------------- 상수 설정 (Configuration) -----------------
// VU 하나하나하
// const token = 'Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJraW0xODE1Iiwicm9sZSI6IlVTRVIiLCJpZCI6ImFlOTc2ZTNiLTViYmItNDhmZC04MmFlLWNlYTU4NzNmODY2YyIsImlhdCI6MTc2NzYxMjM2MiwiZXhwIjoxNzY3NjE1OTYyfQ.GZZqlAXMbGnzrl7U8Br6fN89pr9lblLE4CDf2JHLqEmuiIa5uJ4RJe7uGaKTM2FJftBtcMruyStvbfXPddM-jg';
const wsErrorCounter = new Counter('ws_error_count');
// 메시지 보낸 수
const messageSentCounter = new Counter('message_sent_count');

// 🔧 테스트 모드 설정
const TEST_MODE = {
    CONNECT_ONLY: true,
    SINGLE_SEND: false,   // 연결 후 1회 전송
    REPEAT_SEND: false,  // TPS 여러번 테스트
};

// ----------------- 테스트 옵션 (Test Options) -----------------
export const options = {
    scenarios: {
        //     create_and_join: {
        //         executor: 'ramping-vus', // VU 수를 단계별로 점진적으로 올렸다 내리는방식
        //         startVUs: 0, // 테스트 시작 초반 VU 수
        //         stages: [ // 테스트 목표 설정
        //             {duration: '1m', target: 200 }, // 30초 동안 0 → 10 VU ramp up
        //             {duration: '1m', target: 500 },  // 1분 동안 10 VU 유지
        //             {duration: '1m', target: 800 },  // 10초 동안 VU ramp down 10 → 0
        //             {duration: '30s', target: 0 },
        //         ],
        //         gracefulRampDown: '30s', // 테스트 종료 시 남은 요청 안전하게 처리 대기시간
        //     },
        // },
        create_and_join: {
            executor: 'per-vu-iterations',
            vus: 1,               // VU 수
            iterations: 1,          // 각 VU가 1 iteration (스크립트 한번 )수행
            maxDuration: '5m'
        },
    },
        thresholds: {
            'http_req_failed': ['rate<0.01'], // HTTP 실패율 1퍼 이하
            'http_req_duration': ['p(95)<500'], // HTTP 95퍼 요청 응답시간 < 500ms
            'ws_error_count': ['count<30'], // Ws 에러 수 < 30
        },
    };
function uuid() {
    const pattern = 'xxxxxxxx-xxxx-4xxx-yxxx-xxxxxxxxxxxx';
    return pattern.replace(/[xy]/g, function (c) {
        const r = Math.random() * 16 | 0;
        const v = c === 'x' ? r : (r & 0x3 | 0x8);
        return v.toString(16);
    });
}
// ----------------- 테스트 시나리오 (Test Scenario) -----------------
/*  K6  저수준이라 STOMP 라이브러리가 없기에 일일히 보내줘야함.
    WebSocket 열기
    STOMP CONNECT 전송
    STOMP CONNECTED 수신
    세션 생성
*/
export default function () {
    // 웹소켓 연결 주소
    const url = 'ws://localhost:8080/api/ws-stomp';
    const url1 = 'ws://host.docker.internal:8080/api/ws-stomp'
    let repeatStarted = false;
    let connected = false;

    // STOMP 커넥션 전송 + 수신
    const res = ws.connect(url1, {}, function (socket) {
        socket.on('open', () => {
            // console.log('socket open');

            socket.send(
                'CONNECT\n' +
                'accept-version:1.2\n' +
                'heart-beat:10000,10000\n' +
                '\n' +
                '\x00'
            );
        });

        socket.on('message', (msg) => {
           //  console.log('stomp 연결메시지: ' + msg);

            // ✅ STOMP 연결 확인
            if (msg.startsWith('CONNECTED')) {
                console.log('✅ STOMP handshake successful');
            }
        });

        socket.on('error', (e) => {
          //  console.log(' error:', e.error());
        });

        socket.on('close', () => {
         //   console.log(' socket closed');
        });

        socket.setTimeout(() => {
          //  console.log('⏱ timeout → close');
            socket.close();
        }, 5000);
    });


  //  console.log('WS status:', res && res.status);
  //  console.log('WS error :', res && res.error);
}
// K6 Stomp 구독연결을 할 수 없음.
//         console.log("구독 시작");
//         setTimeout(() => {
//             console.log("구독 시작");
//             socket.send(
//                 'SUBSCRIBE\n' +
//                 'id:sub-0\n' +
//                 'destination:/topic/chat/room\n' +
//                 '\n' +
//                 '\x00'
//             );
//         }, 100);
//     }
//
//     if (msg.startsWith('MESSAGE')) {
//         console.log('구독 메시지 받음:\n', msg);
//     }
// });
// 메시지 발행 서버에서 처리 불가
// if (!TEST_MODE.CONNECT_ONLY && TEST_MODE.SINGLE_SEND) {
//     console.log("메시지 전송테스트")
//     const messagePayload = JSON.stringify({
//         message: "안녕하세요 처음 들어왔어요.",
//         sentAt: Date.now()
//     });
//
//     const frame = `
//                     SEND
//                     destination:/pub/chat/message
//                     content-type:application/json
//                     content-length:${messagePayload.length}
//
//                     ${messagePayload}\x00`;
//
//     socket.send(frame.trim());
//     console.log('메시지 전송 완료:\n', messagePayload);
//     messageSentCounter.add(1);
// }
//
// // 반복 전송 테스트
// if (TEST_MODE.REPEAT_SEND) {
//     socket.setInterval(() => {
//         const messagePayload = JSON.stringify({
//             message: "repeat send test",
//             sentAt: Date.now(),
//         });
//
//         const frame =
//             `SEND
//                             destination:/pub/chat/message
//                             content-type:application/json
//                             content-length:${messagePayload.length}
//
//                             ${messagePayload}\x00`;
//
//         socket.send(frame.trim());
//         messageSentCounter.add(1);
//         //  console.log('반복 메시지 전송:', messagePayload);
//     }, 1000); // 1초 간격
// }
