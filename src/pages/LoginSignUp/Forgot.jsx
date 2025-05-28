import { Link } from "react-router-dom";
import "../../styles/LoginSignUp/Forgot.css";

export default function Forgot() {
  return (
    <div className="forgot-body">
      <div className="forget-container">
        <div className="forget-left-section">
          <h2 className="forget-title">Forgot Password ?</h2>
          <div className="forget-input-container">
            <input type="text" placeholder="Email address or Username" className="forget-input-field" />
          </div>
          <p className="forget-back-link">
            Back to <Link to="/Login">Log in</Link>
          </p>
          <button className="forget-send-btn">Send</button>
          <div className="divider">
            <span>OR</span>
          </div>
          <div className="forget-social-icons">
            <i className="fab fa-google"></i>
            <i className="fab fa-facebook-f"></i>
            <i className="fab fa-instagram"></i>
          </div>
        </div>
        <div className="forget-right-section">
          <h1 className="forget-org-name">FilmyAI</h1>
          <p className="forget-org-tag-line">Start your journey today!</p>
        </div>
      </div>
    </div>
  );
}
