import { useEffect, useRef } from "react";
import "../../styles/LandingPage/About.css";

export default function About() {
  const aboutSubtitleRef = useRef(null);
  const aboutDescriptionRef = useRef(null);

  useEffect(() => {
    if (!aboutSubtitleRef.current || !aboutDescriptionRef.current) return;

    const observer = new IntersectionObserver(
      ([entry]) => {
        if (entry.isIntersecting && !entry.target.dataset.animated) {
          entry.target.dataset.animated = "true";
          entry.target.classList.add("slide-up");

          aboutSubtitleRef.current.addEventListener(
            "transitionend",
            () => {
              aboutDescriptionRef.current.classList.add("fade-in");
            },
            { once: true }
          );

          observer.unobserve(entry.target);
        }
      },
      { threshold: 0.1 }
    );

    observer.observe(aboutSubtitleRef.current);
    return () => observer.disconnect();
  }, []);

  return (
    <div id="about" className="about-section section">
      <div className="about-container container">
        <div className="about-section-title">
          <p className="about-subtitle" ref={aboutSubtitleRef}>
            <span className="first-word">What is </span>
            <span className="second-word">FilmyAI?</span>
          </p>
        </div>
        <p className="about-description" ref={aboutDescriptionRef}>
          Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged.
        </p>
      </div>
    </div>
  );
}
