import "../../styles/LandingPage/Blog.css";
import { motion } from "framer-motion";
import Tilt from "react-parallax-tilt";

const fadeIn = (delay = 0) => ({
  hidden: { opacity: 0, y: 40 },
  show: { opacity: 1, y: 0, transition: { duration: 0.6, delay } },
});

export default function Blog() {
  const blogPosts = Array(6).fill({
    title: "Blog Title",
    image: "/images/landingPage/lens.png",
  });

  return (
    <div id="blog" className="blog-section section">
      <div className="blog-container container">
        <div id="blog-cards" className="blog-cards">
          {blogPosts.map((post, index) => (
            <motion.div
              key={index}
              variants={fadeIn(index * 0.1)}
              initial="hidden"
              whileInView="show"
              viewport={{ once: true, amount: 0.2 }}
            >
              <Tilt
                className="blog-card"
                tiltMaxAngleX={15}
                tiltMaxAngleY={15}
                scale={1.05}
                transitionSpeed={400}
              >
                <img className="card-image" src={post.image} alt="Blog" />
                <div className="blog-name">
                  <a href="javascript:void(0);">
                    <h5 className="blog-title">{post.title}</h5>
                  </a>
                </div>
              </Tilt>
            </motion.div>
          ))}
        </div>
      </div>
    </div>
  );
}
