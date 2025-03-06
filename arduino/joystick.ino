#define JOYSTICK_X A0
#define JOYSTICK_Y A1
#define BUTTON_SW 2

void setup() {
    Serial.begin(9600);
    pinMode(BUTTON_SW, INPUT_PULLUP); 
}

void loop() {
    int xValue = analogRead(JOYSTICK_X);
    int yValue = analogRead(JOYSTICK_Y);
    int button = digitalRead(BUTTON_SW);

    Serial.print(xValue);
    Serial.print(",");
    Serial.print(yValue);
    Serial.print(",");
    Serial.println(button);

    delay(50);
}
