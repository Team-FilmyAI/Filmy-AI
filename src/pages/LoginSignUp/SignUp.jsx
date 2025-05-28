import { useState } from "react";
import { Link } from "react-router-dom";
import "../../styles/LoginSignUp/SignUp.css";

export default function Signup() {
  const [formType, setFormType] = useState("user");
  const [popupVisible, setPopupVisible] = useState(false);

  return (
    <div className="signup-body">
      <div className="signup-container">
        <div className="signup-left-section">
          <div className="signup-form-container">
            <h2 className="signup-title">Sign Up</h2>

            <div className="signup-toggle">
              <label>
                <input type="radio" name="type" value="user" checked={formType === "user"} onChange={() => setFormType("user")} className="signup-form-radio-input" />
                <span>User</span>
              </label>
              <label>
                <input type="radio" name="type" value="business" checked={formType === "business"} onChange={() => setFormType("business")} className="signup-form-radio-input" />
                <span>Business</span>
              </label>
            </div>

            <form className="signup-form" onSubmit={(e) => e.preventDefault()}>
              {formType === "user" && (
                <div className="signup-user-form">
                  <input type="text" placeholder="First Name" className="signup-form-input" />
                  <input type="text" placeholder="Last Name" className="signup-form-input" />
                  <input type="email" placeholder="Email address" className="signup-form-input" />
                  <input type="password" placeholder="Password" className="signup-form-input" />
                  <div className="signup-terms">
                    <input type="checkbox" id="terms" />
                    <label htmlFor="terms">
                      I agree to{" "}
                      <a href="/documents/Terms.pdf" target="_blank" rel="noopener noreferrer">
                        Terms & Conditions
                      </a>
                    </label>
                  </div>
                </div>
              )}

              {formType === "business" && (
                <div className="signup-business-form">
                  <input type="text" placeholder="Business Name" className="signup-form-input" />
                  <input type="email" placeholder="Email address" className="signup-form-input" />
                  <input type="password" placeholder="Password" className="signup-form-input" />
                  <div className="signup-terms">
                    <input type="checkbox" id="terms" />
                    <label htmlFor="terms">
                      I agree to{" "}
                      <a href="/documents/Terms.pdf" target="_blank" rel="noopener noreferrer">
                        Terms & Conditions
                      </a>
                    </label>
                  </div>
                </div>
              )}

              <button type="button" className="signup-btn" onClick={() => setPopupVisible(true)}>
                Sign Up
              </button>
            </form>

            <p className="signup-login-prompt">
              Already have an account? <Link to="/Login">Click here</Link> to login
            </p>
            <div className="divider">
              <span>OR</span>
            </div>

            <div className="signup-social-icons">
              <i class="fab fa-google"></i>
              <i class="fab fa-facebook-f"></i>
              <i class="fab fa-instagram"></i>
            </div>
          </div>
        </div>
        <div className="signup-right-section">
          <h1 className="login-org-name">FilmyAI</h1>
          <p className="login-org-tag-line">Start your journey today!</p>
        </div>

        {popupVisible && (
          <>
            <div className="signup-overlay" onClick={() => setPopupVisible(false)}></div>
            <div className="signup-popup">
              <p>Thank you for signing up!</p>
              <button onClick={() => setPopupVisible(false)} className="signup-close-btn">
                Close
              </button>
            </div>
          </>
        )}
      </div>
    </div>
  );
}
