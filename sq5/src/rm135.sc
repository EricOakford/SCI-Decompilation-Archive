;;; Sierra Script 1.0 - (do not remove this comment)
(script# 135)
(include game.sh) (include "135.shm")
(use Main)
(use starCon)
(use SpeakWindow)
(use Talker)
(use Osc)
(use RandCyc)
(use Feature)
(use LoadMany)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	rm135 0
	smartTalker 1
	dumbTalker 2
	rogerTalker 15
)

(local
	[local0 10] = [3 4 4 2 4 2 4 3]
	[local10 10] = [2 3 0 4 1 4 1 1 2 4]
	rogShocked
)
(instance rm135 of Room
	(properties
		noun N_ROOM
		picture 22
		style (| BLACKOUT FADEOUT)
	)
	
	(method (init)
		(self setRegions: rgStarCon)
		(LoadMany RES_VIEW 120 121 122 123 124 128)
		(LoadMany RES_MESSAGE 135)
		(theGame handsOff:)
		(Bset fAttendedClass)
		(SolvePuzzle f135TakeTest 5)
		(ego view: 121)
		(blobBody init: setOnMeCheck: ftrControl cBLUE)
		(blueGuyBody init: setOnMeCheck: ftrControl cGREEN)
		(dumbCadetBody init: setOnMeCheck: ftrControl cCYAN)
		(greenGirlBody init: setOnMeCheck: ftrControl cRED)
		(klingonBody init: setOnMeCheck: ftrControl cMAGENTA)
		(ramBody init: setOnMeCheck: ftrControl cBROWN)
		(smartCadetBody init: setOnMeCheck: ftrControl cLGREY)
		(rogDesk init: setOnMeCheck: ftrControl cYELLOW)
		(smartCadetDesk init: setOnMeCheck: ftrControl cLMAGENTA)
		(dumbCadetDesk init: setOnMeCheck: ftrControl cWHITE)
		(classDoor init: setOnMeCheck: ftrControl cLRED)
		(generalDesk init: setOnMeCheck: ftrControl cLBLUE)
		(cyclopsEye init: setScript: sCyclopsEye)
		(hornedGuy init: setScript: sHornedGuy)
		(redGuy init:)
		(bubbleHead init:)
		(smartEyes init: setScript: sSmartEyes)
		(smartHand init: setCycle: RandCycle)
		(super init:)
		(switch prevRoomNum
			(137
				(if (> curTestQuestion 9)
					(curRoom setScript: sTestOver)
				else
					(cheatDroid
						posn: (Random 20 320) 55
						init:
						setLoop: 0
						setScript: sCheatDroid
					)
					(curRoom setScript: sRogTakeTest)
				)
			)
			(else 
				(curRoom setScript: sTalkToTeach)
			)
		)
	)
	
	(method (dispose)
		(super dispose:)
	)
)

(instance sTalkToTeach of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(= testScore 0)
				(= curTestQuestion 0)
				(ego
					view: 120
					setLoop: 0
					setCel: 0
					posn: 96 41
					setPri: 14
					signal: ignrAct
					setScale: 0
					init:
				)
				(= seconds 1)
			)
			(1
				(ego view: 121 setLoop: 0 setCel: 0 posn: 104 49)
				(theMusic2 number: 123 setLoop: 1 play: self)
			)
			(2
				(messager say: N_TALK_TO_TEACHER NULL NULL 1 self)
			)
			(3
				(theMusic2 number: 1231 setLoop: 1 play: self)
			)
			(4
				(ego setLoop: 5 setCel: 0 posn: 104 48)
				(= rogShocked TRUE)
				(theMusic1 number: 6 setLoop: -1 play:)
				(= ticks 10)
			)
			(5
				(messager say: N_TALK_TO_TEACHER NULL NULL 2 self)
			)
			(6
				(theMusic1 stop:)
				(ego setLoop: 0 setCel: 0 posn: 104 49)
				(= rogShocked FALSE)
				(= cycles 1)
			)
			(7
				(theMusic2 number: 1232 setLoop: 1 play: self)
			)
			(8
				(messager say: N_TALK_TO_TEACHER NULL NULL 3 self)
			)
			(9
				(theMusic2 number: 1231 setLoop: 1 play: self)
			)
			(10
				(messager say: N_TALK_TO_TEACHER NULL NULL 4 self)
			)
			(11
				(theMusic1 number: 7 setLoop: -1 play:)
				(ego setLoop: 2 setCel: 0 posn: 111 71)
				(blueGuy init: setScript: sBlueGuy)
				(klingon init: setScript: sKlingon)
				(theGame handsOn:)
				(curRoom newRoom: 137)
				(self dispose:)
			)
		)
	)
)

(instance sRogTakeTest of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego setLoop: 2 setCel: 0 posn: 111 71 init:)
				(rogShiftyEyes setCycle: Oscillate init:)
				(theGame handsOn:)
				(theIconBar disable: 0 4)
				(self dispose:)
			)
		)
	)
)

(instance sLookAtSmart of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(SolvePuzzle f135CheatTest 5)
				(if (and (< 6 curTestQuestion) (< curTestQuestion 9))
					(cheatAnswer loop: 3)
				else
					(cheatAnswer loop: 2)
				)
				(cheatAnswer posn: 50 30 cel: [local0 curTestQuestion] init:)
				(cheatQuestion posn: 46 16 cel: curTestQuestion init:)
				(= seconds 3)
			)
			(1
				(theMusic2 number: 120 setLoop: 1 play: self)
				(cheatAnswer dispose:)
				(cheatQuestion dispose:)
			)
			(2
				(ego setScript: sRogTakeTest)
				(= seconds 1)
			)
			(3
				(bubbleHead setCel: 1 posn: 30 40)
				(smartEyes hide:)
				(smartHand hide:)
				(= seconds 2)
			)
			(4
				(bubbleHead setCel: 0 posn: 31 41)
				(smartEyes show:)
				(smartHand show:)
				(cheatDroid setScript: sCheatDroid)
				(theGame handsOn:)
				(theIconBar disable: ICON_WALK ICON_ORDER)
				(self dispose:)
			)
		)
	)
)

(instance sLookAtDumb of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if (and (< 6 curTestQuestion) (< curTestQuestion 9))
					(cheatAnswer loop: 3)
				else
					(cheatAnswer loop: 2)
				)
				(cheatAnswer posn: 250 30 cel: [local10 curTestQuestion] init:)
				(cheatQuestion posn: 246 16 cel: curTestQuestion init:)
				(= seconds 3)
			)
			(1
				(theMusic2 number: 120 setLoop: 1 play: self)
				(cheatAnswer dispose:)
				(cheatQuestion dispose:)
			)
			(2
				(ego setScript: sRogTakeTest)
				(= seconds 1)
			)
			(3
				(redGuy setCel: 1 posn: 266 49)
				(= seconds 2)
			)
			(4
				(redGuy setCel: 0 posn: 274 46)
				(cheatDroid setScript: sCheatDroid)
				(theGame handsOn:)
				(theIconBar disable: ICON_WALK ICON_ORDER)
				(self dispose:)
			)
		)
	)
)

(instance sBlueGuy of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(blueGuy show:)
				(= seconds (Random 3 6))
			)
			(1
				(blueGuy hide:)
				(= seconds (Random 3 6))
			)
			(2 (= state -1) (= cycles 1))
		)
	)
)

(instance sSmartEyes of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= seconds (* (Random 1 3) 3))
			)
			(1
				(smartEyes setCycle: Oscillate 1 self)
			)
			(2 (= state -1) (= cycles 1))
		)
	)
)

(instance sHornedGuy of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(hornedGuy show:)
				(= seconds (Random 1 4))
			)
			(1
				(hornedGuy hide:)
				(= seconds (Random 1 4))
			)
			(2 (= state -1) (= cycles 1))
		)
	)
)

(instance sKlingon of Script

	(method (changeState newState)
		(switch (= state newState)
			(0
				(klingon show:)
				(= seconds (Random 2 5))
			)
			(1
				(klingon hide:)
				(= seconds (Random 2 5))
			)
			(2 (= state -1) (= cycles 1))
		)
	)
)

(instance sCyclopsEye of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(cyclopsEye setCycle: EndLoop self)
			)
			(1 (= seconds (Random 1 3)))
			(2
				(cyclopsEye setCycle: BegLoop self)
			)
			(3 (= seconds (Random 3 6)))
			(4 (= state -1) (= cycles 1))
		)
	)
)

(instance sCheatDroid of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(cheatDroid cel: 3)
				(= seconds (Random 2 4))
			)
			(1
				(cheatDroid cel: 4)
				(= ticks 4)
			)
			(2
				(cheatDroid cel: 5)
				(= ticks 4)
			)
			(3
				(cheatDroid cel: 0)
				(= seconds (Random 2 4))
			)
			(4
				(cheatDroid cel: 1)
				(= ticks 4)
			)
			(5
				(cheatDroid cel: 2)
				(= state -1)
				(= ticks 4)
			)
		)
	)
)

(instance sCheatAlert of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(droidAmbient dispose:)
				(cheatDroid xStep: 10)
				(if (> (cheatDroid x?) 160)
					(cheatDroid setMotion: MoveTo 240 (cheatDroid y?) self)
				else
					(cheatDroid setMotion: MoveTo 80 (cheatDroid y?) self)
				)
			)
			(1
				(cond 
					((== (cheatDroid cel?) 2) (cheatDroid setCel: 1) (= cycles 1))
					((> (cheatDroid cel?) 3) (cheatDroid setCycle: EndLoop self))
					(else (= cycles 1))
				)
			)
			(2
				(theMusic2 number: 122 setLoop: -1 play:)
				(cheatDroid setLoop: 1 setCel: 0)
				(= ticks 4)
			)
			(3
				(cheatDroid setLoop: 1 setCel: 1)
				(= ticks 4)
			)
			(4
				(alertTop
					posn: (- (cheatDroid x?) 9) (- (cheatDroid y?) 16)
					init:
				)
				(alertEyes
					x: (- (cheatDroid x?) 9)
					y: (- (cheatDroid y?) 6)
					init:
					setCycle: Forward
				)
				(alertBeacon
					posn: (- (cheatDroid x?) 4) (+ (cheatDroid y?) 10)
					init:
				)
				(alertBeaconLight
					posn: (- (cheatDroid x?) 2) (+ (cheatDroid y?) 15)
					init:
				)
				(alertBottom
					posn: (+ (cheatDroid x?) 2) (+ (cheatDroid y?) 37)
					init:
				)
				(= ticks 4)
			)
			(5
				(alertBeaconLight setCycle: Forward)
				(alertBeacon setCycle: Forward)
				(cyclopsEye dispose:)
				(klingon dispose:)
				(blueGuy dispose:)
				(hornedGuy dispose:)
				(= ticks 1)
			)
			(6
				(alertTop hide:)
				(alertBottom hide:)
				(= ticks 2)
			)
			(7
				(if (not (ego script?))
					(ego setScript: sRogCaught)
				)
				(alertTop show:)
				(alertBottom show:)
				(= ticks 2)
			)
			(8
				(= state 3)
				(= cycles 1)
			)
		)
	)
)

(instance sRogCaught of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(rogShiftyEyes hide:)
				(theMusic1 number: 8 setLoop: 1 play:)
				(ego loop: 5 cel: 1 posn: 113 75)
				(= seconds 3)
			)
			(1
				(messager say: N_TALK_TO_TEACHER NULL NULL 8 self)
			)
			(2
				(curRoom newRoom: 195)
				(self dispose:)
			)
		)
	)
)

(instance sTestOver of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theMusic1 fade:)
				(ego loop: 0 cel: 0 posn: 104 49 init:)
				(= seconds 3)
			)
			(1
				(theMusic1 number: 5 setLoop: -1 play:)
				(messager say: N_TALK_TO_TEACHER NULL NULL 5 self)
			)
			(2
				(theMusic2 number: 123 setLoop: 1 play: self)
			)
			(3
				(if (> testScore 4)
					(messager say: N_TALK_TO_TEACHER NULL NULL 6 self)
				else
					(messager say: N_TALK_TO_TEACHER NULL NULL 7 self)
				)
			)
			(4
				(if (> testScore 4)
					(starCon setScript: (ScriptID 109 2))
					(= cycles 2)
				else
					(curRoom newRoom: 195)
					(self dispose:)
				)
			)
			(5
				(curRoom newRoom: 123)
				(self dispose:)
			)
		)
	)
)

(instance rogShiftyEyes of Prop
	(properties
		x 169
		y 95
		noun N_BLOB
		modNum 1
		view 121
		loop 6
		priority 14
		signal (| ignrAct fixPriOn)
	)
)

(instance hornedGuy of Prop
	(properties
		x 204
		y 50
		noun N_RAM
		view 124
		loop 4
		priority 2
		signal fixPriOn
		detailLevel 2
	)
)

(instance blueGuy of Prop
	(properties
		x 162
		y 38
		noun N_BLUE_GUY
		view 124
		loop 2
		priority 1
		signal fixPriOn
		detailLevel 2
	)
)

(instance klingon of Prop
	(properties
		x 98
		y 49
		noun N_KLINGON
		view 124
		loop 3
		priority 2
		signal fixPriOn
		detailLevel 2
	)
)

(instance cyclopsEye of Prop
	(properties
		x 80
		y 77
		noun N_BLOB
		view 124
		loop 5
		priority 1
		signal fixPriOn
		detailLevel 2
	)
)

(instance redGuy of Prop
	(properties
		x 274
		y 46
		noun N_DUMB
		view 124
		priority 6
		signal fixPriOn
	)
)

(instance bubbleHead of Prop
	(properties
		x 31
		y 41
		noun N_SMART
		view 123
		priority 6
		signal fixPriOn
	)
)

(instance cheatDroid of Actor
	(properties
		x 350
		y 55
		noun N_CHEAT_DROID
		view 122
		priority 4
		signal fixPriOn
	)
	
	(method (init)
		(super init:)
		(droidAmbient init: setCycle: Forward)
		(theMusic2 number: 121 setLoop: -1 play:)
	)
	
	(method (doit)
		(if
			(and
				(== (cheatDroid mover?) 0)
				(not (== (cheatDroid script?) sCheatAlert))
			)
			(if (> (cheatDroid x?) 300)
				(cheatDroid setMotion: MoveTo 20 55)
			else
				(cheatDroid setMotion: MoveTo 320 55)
			)
		)
		(super doit:)
		(cond 
			((== (self cel?) 0) (droidAmbient loop: 6 show:))
			((== (self cel?) 3) (droidAmbient loop: 7 show:))
			(else (droidAmbient hide:))
		)
		(droidAmbient x: (self x?) y: (self y?))
	)
	
	(method (dispose)
		(theMusic2 stop:)
		(super dispose:)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_LOOK
				(if (== (self cel?) 3)
					(messager say: noun V_LOOK C_FACING_AWAY 0 self)
				else
					(messager say: noun V_LOOK C_FACING_ROGER 0 self)
				)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance droidAmbient of Prop
	(properties
		noun N_CHEAT_DROID
		view 122
		loop 6
		priority 4
		signal (| ignrAct fixPriOn)
	)
)

(instance alertTop of Prop
	(properties
		noun N_CHEAT_DROID
		view 122
		loop 2
		priority 5
		signal fixPriOn
	)
)

(instance alertEyes of Prop
	(properties
		noun N_CHEAT_DROID
		view 122
		loop 5
		priority 5
		signal fixPriOn
	)
)

(instance alertBeacon of Prop
	(properties
		noun N_CHEAT_DROID
		view 122
		loop 4
		priority 5
		signal fixPriOn
	)
)

(instance alertBottom of Prop
	(properties
		noun N_CHEAT_DROID
		view 122
		loop 2
		cel 2
		priority 5
		signal fixPriOn
	)
)

(instance alertBeaconLight of Prop
	(properties
		noun N_CHEAT_DROID
		view 122
		loop 3
		priority 6
		signal fixPriOn
		cycleSpeed 0
	)
)

(instance smartEyes of Prop
	(properties
		x -3
		y 93
		noun N_SMART
		view 123
		loop 2
		priority 14
		signal fixPriOn
		cycleSpeed 20
	)
)

(instance smartHand of Prop
	(properties
		x -10
		y 155
		noun N_SMART
		view 123
		loop 1
		priority 15
		signal fixPriOn
		cycleSpeed 15
	)
)

(instance rogMouth of Prop
	(properties
		nsTop 11
		view 121
	)
)

(instance dumbMouth of Prop
	(properties
		nsTop 1
		nsLeft 1
		view 123
		loop 4
	)
)

(instance smartMouth of Prop
	(properties
		nsTop 1
		nsLeft 1
		view 123
		loop 4
	)
)

(instance rogerTalker of Talker
	(properties
		x 104
		view 121
		talkWidth 170
		textX -70
	)
	
	(method (init)
		(= talkWidth (WhichLanguage 250 170 250 170 170))
		(= font userFont)
		(= systemWindow SpeakWindow)
		(systemWindow
			tailX: (WhichLanguage 164 164 164 164 124)
			tailY: (WhichLanguage 42 46 42 46 46)
			xOffset: (WhichLanguage 5 5 5 5 -5)
		)
		(if rogShocked
			(rogMouth loop: 7 nsTop: 13 nsLeft: 42)
			(self loop: 5 cel: 0 y: 46)
		else
			(rogMouth loop: 1 nsTop: 11 nsLeft: 43)
			(self loop: 0 cel: 0 y: 49)
		)
		(super init: 0 0 rogMouth &rest)
	)
	
	(method (dispose)
		(= systemWindow gSq5Win_2)
		(super dispose: &rest)
	)
)

(instance dumbTalker of Talker
	(properties
		x 260
		y 49
		view 123
		loop 3
		talkWidth 130
		textX -100
	)
	
	(method (init)
		(= font userFont)
		((= systemWindow SpeakWindow)
			tailX: 260
			tailY: 43
			xOffset: -45
		)
		(super init: 0 0 dumbMouth &rest)
	)
	
	(method (dispose)
		(= systemWindow gSq5Win_2)
		(super dispose: &rest)
	)
)

(instance smartTalker of Talker
	(properties
		x 30
		y 49
		view 123
		loop 3
		talkWidth 170
		textX 40
	)
	
	(method (init)
		(= font userFont)
		((= systemWindow SpeakWindow)
			tailX: 40
			tailY: 43
			xOffset: 60
		)
		(super init: 0 0 smartMouth &rest)
	)
	
	(method (dispose)
		(= systemWindow gSq5Win_2)
		(super dispose: &rest)
	)
)

(instance cheatAnswer of View
	(properties
		x 50
		y 30
		view 128
		loop 2
		priority 15
		signal fixPriOn
	)
)

(instance cheatQuestion of View
	(properties
		x 46
		y 16
		view 128
		priority 15
		signal fixPriOn
	)
)

(instance rogDesk of Feature
	(properties
		x 157
		y 136
		noun N_DESK
		onMeCheck cYELLOW
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_DO
				(curRoom newRoom: 137)
			)
			(V_LOOK
				(curRoom newRoom: 137)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance smartCadetDesk of Feature
	(properties
		x 12
		y 149
		noun N_DESK
		onMeCheck cLMAGENTA
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_LOOK
				(theGame handsOff:)
				(cheatDroid setScript: 0)
				(rogShiftyEyes hide:)
				(theMusic2 number: 120 setLoop: 1 play:)
				(ego setLoop: 3 setCel: 0 posn: 107 46)
				(if (not (== (cheatDroid cel?) 3))
					(cheatDroid setScript: sCheatAlert)
				else
					(curRoom setScript: sLookAtSmart)
				)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance dumbCadetDesk of Feature
	(properties
		x 304
		y 127
		noun N_DESK
		onMeCheck cWHITE
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_LOOK
				(theGame handsOff:)
				(cheatDroid setScript: 0)
				(rogShiftyEyes hide:)
				(theMusic2 number: 120 setLoop: 1 play:)
				(ego setLoop: 4 setCel: 0 posn: 117 61)
				(if (not (== (cheatDroid cel?) 3))
					(cheatDroid setScript: sCheatAlert)
				else
					(curRoom setScript: sLookAtDumb)
				)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance blobBody of Feature
	(properties
		x 4
		y 20
		noun N_BLOB
		onMeCheck cBLUE
	)
)

(instance blueGuyBody of Feature
	(properties
		x 4
		y 20
		noun N_BLUE_GUY
		onMeCheck cCYAN
	)
)

(instance dumbCadetBody of Feature
	(properties
		x 4
		y 20
		noun N_DUMB
		onMeCheck cCYAN
	)
)

(instance greenGirlBody of Feature
	(properties
		x 4
		y 20
		noun N_GREEN_GIRL
		onMeCheck cRED
	)
)

(instance klingonBody of Feature
	(properties
		x 4
		y 20
		noun N_KLINGON
		onMeCheck cMAGENTA
	)
)

(instance ramBody of Feature
	(properties
		x 4
		y 20
		noun N_RAM
		onMeCheck cBROWN
	)
)

(instance smartCadetBody of Feature
	(properties
		x 4
		y 20
		noun N_SMART
		onMeCheck cLGREY
	)
)

(instance classDoor of Feature
	(properties
		x 4
		y 20
		noun N_DOOR
		onMeCheck cLRED
	)
)

(instance generalDesk of Feature
	(properties
		x 4
		y 20
		noun N_DESK
		onMeCheck cLBLUE
	)
)
