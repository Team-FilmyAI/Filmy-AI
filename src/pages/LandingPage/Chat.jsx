import { useEffect, useRef } from "react";
import "../../styles/LandingPage/Chat.css";

export default function Chat() {
  const chatRef = useRef(null);
  const chatCloseRef = useRef(null);
  const navContainerRef = useRef(null);

  useEffect(() => {
    const chat = chatRef.current;
    const chatClose = chatCloseRef.current;
    const navContainer = navContainerRef.current;

    if (chat && chatClose) {
      chat.addEventListener("click", () => {
        chat.classList.remove("collapsed");
        chat.classList.add("expanded");
      });

      chatClose.addEventListener("click", (event) => {
        event.stopPropagation();
        chat.classList.remove("expanded");
        chat.classList.add("collapsed");
      });
    }

    if (navContainer) {
      navContainer.addEventListener("click", (event) => {
        if (event.target.classList.contains("nav-item")) {
          const currentActive = navContainer.querySelector(".active");
          if (currentActive) {
            currentActive.classList.remove("active");
          }
          event.target.classList.add("active");
        }
      });
    }

    // Cleanup
    return () => {
      chat?.removeEventListener("click", () => {});
      chatClose?.removeEventListener("click", () => {});
      navContainer?.removeEventListener("click", () => {});
    };
  }, []);

  return (
    <div id="chat" ref={chatRef} className="chat collapsed">
      <div className="chat-btn">
        <i className="fa-solid fa-question"></i>
      </div>
      <div className="chat-container">
        <i id="chatClose" ref={chatCloseRef} className="chat-close fa-solid fa-x"></i>
        <div className="chat-header">
          <h6 className="flex-grow-1 text-center">
            <b>Have a Question?</b>
          </h6>
        </div>
        <div className="chat-body">
          <div className="message-body">
            <div className="bot-message">
              <p>Hey! I am bot.</p>
            </div>
            <div className="my-message">
              <p>It's a message.</p>
            </div>
          </div>
          <div className="message-form">
            <input id="sendBtn" type="text" className="message-input" placeholder="Type here..." />
            <i className="fa-solid fa-paper-plane send-btn"></i>
          </div>
        </div>
      </div>
    </div>
  );
}
