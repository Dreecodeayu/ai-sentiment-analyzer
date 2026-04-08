import { useState } from 'react';
import { TextField, Button, Card, CardContent, Typography } from "@mui/material";
function App() {
  const [text, setText] = useState("");
  const [result, setResult] = useState("null");

  const handleAnalyze = async() => {
    const response = await fetch("http://localhost:8080/analyze",{
      method:"POST",
      headers:{
        "Content-Type":"application/json",
      },
      body:JSON.stringify({text:text}),
    });
    const data = await response.json();
    setResult(data);
  };
  return (
      <div style={{ display: "flex", justifyContent: "center", marginTop: "50px" }}>
      
      <Card style={{ width: "400px", padding: "20px" }}>
        <CardContent>

          <Typography variant="h5" gutterBottom>
            Sentiment Analyzer
          </Typography>

          <TextField
            fullWidth
            label="Enter text"
            value={text}
            onChange={(e) => setText(e.target.value)}
          />

          <br /><br />

          <Button variant="contained" onClick={handleAnalyze}>
            Analyze
          </Button>

          <br /><br />

          {result && (
            <div>
              <Typography>Sentiment: {result.sentiment}</Typography>
              <Typography>Confidence: {result.confidence}</Typography>
            </div>
          )}

        </CardContent>
      </Card>

    </div>

  );
}

export default App
