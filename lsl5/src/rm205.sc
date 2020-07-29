;;; Sierra Script 1.0 - (do not remove this comment)
(script# 205)
(include game.sh)
(use Main)
(use LLRoom)
(use Talker)
(use RandCyc)
(use Motion)
(use Actor)
(use System)

(public
	rm205 0
)

(local
	theScript
)
(instance rm205 of LLRoom
	
	(method (init &tmp temp0)
		(super init:)
		(self setScript: sCartoon)
	)
)

(instance sCartoon of Script
	
	(method (init)
		(keyDownHandler addToFront: self)
		(mouseDownHandler addToFront: self)
		(SetFFRoom 1000 self)
		(theIconBar disable: ICON_CONTROL)
		(super init: &rest)
	)
	
	(method (dispose)
		(keyDownHandler delete: self)
		(mouseDownHandler delete: self)
		(theIconBar enable: ICON_CONTROL)
		(super dispose:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(DrawPic 1 SCROLLLEFT)
				(theGame setCursor: theCursor TRUE 300 180)
				(switch (++ gameState)
					(1
						(curRoom picture: 120 curPic: 120)
						(theMusic2 number: 210 setLoop: -1 play: 127)
						(self setScript: sMobWantsCane self)
					)
					(2
						(curRoom picture: 215 curPic: 215)
						(theMusic2 number: 215 setLoop: -1 play: 127)
						(self setScript: sScruemallGetsCall self)
					)
					(3
						(curRoom picture: 220 curPic: 220)
						(theMusic2 number: 215 setLoop: -1 play: 127)
						(self setScript: sCaneGetsBigGrant self)
					)
					(4
						(curRoom picture: 225 curPic: 225)
						(theMusic2 number: 215 setLoop: -1 play: 127)
						(self setScript: sCaneWorksCongress self)
					)
				)
			)
			(1
				(if script (script caller: 0 dispose:))
				(= theScript self)
				(DrawPic 1 SCROLLRIGHT)
				(DoDisplay 2 myDisplayColor 205 0)
				(theMusic2 fade: 0 15 5 1)
				(theMusic number: 204 loop: -1 play: 0 fade: 127 15 5 0)
				(= seconds 3)
			)
			(2
				(curRoom newRoom: 200)
			)
		)
	)
	
	(method (handleEvent event)
		(if (OneOf (event type?) mouseDown keyDown)
			(if theScript (theScript cue:))
		else
			(super handleEvent: event)
		)
	)
)

(instance sMobWantsCane of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= theScript self)
				(DoDisplay 4 myDisplayColor 205 1)
				(= seconds 5)
			)
			(1
				(= theScript 0)
				(DrawPic 120 SCROLLLEFT)
				(leftEyebrow init:)
				(rightEyebrow init:)
				(cigar init:)
				(hand init:)
				(= ticks 30)
			)
			(2
				(cigar setCycle: RandCycle)
				(leftEyebrow setCycle: RandCycle)
				(rightEyebrow setCycle: RandCycle)
				(TimePrint 205 2 80 {Mr. Bigg} 108 self 67 -1 185)
			)
			(3
				(cigar setCycle: 0)
				(hand setCycle: Forward)
				(leftEyebrow setCycle: BegLoop)
				(rightEyebrow setCycle: BegLoop)
				(Say Vinnie 205 3 #dispose #caller self)
			)
			(4 (= ticks 45))
			(5
				(Say Vinnie 205 4 #dispose #caller self)
			)
			(6 (= ticks 45))
			(7
				(Say Bruno 205 5 #dispose #caller self)
			)
			(8 (= ticks 45))
			(9
				(Say Bruno 205 6 #dispose #caller self)
			)
			(10 (= ticks 45))
			(11
				(Say Bruno 205 7 #dispose #caller self)
			)
			(12
				(TimePrint 205 8)
				(= ticks 45)
			)
			(13
				(leftEyebrow setCycle: RandCycle)
				(rightEyebrow setCycle: RandCycle)
				(hand setCycle: EndLoop)
				(cigar setCycle: RandCycle)
				(TimePrint 205 9 80 {Mr. Bigg} 108 self 67 -1 185)
			)
			(14
				(leftEyebrow setCycle: BegLoop)
				(rightEyebrow setCycle: BegLoop)
				(hand setCycle: BegLoop)
				(TimePrint 205 10)
				(= ticks 30)
			)
			(15
				(cigar setCycle: Forward)
				(TimePrint 205 11
					#title {Mr. Bigg} #dispose self #at -1 185)
			)
			(16 (self dispose:))
		)
	)
)

(instance sScruemallGetsCall of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= theScript self)
				(DoDisplay 3 myDisplayColor 205 12)
				(= seconds 5)
			)
			(1
				(= theScript 0)
				(DrawPic 215 SCROLLLEFT)
				(= cycles 2)
			)
			(2
				(TimePrint 205 13
					#at -1 185
					#width 280
				)
				(= ticks 45)
			)
			(3
				(Say Silas_Scruemall 205 14 #dispose #caller self)
			)
			(4 (= ticks 45))
			(5
				(Say Silas_Scruemall 205 15 #dispose #caller self)
			)
			(6 (= ticks 30))
			(7 (self dispose:))
		)
	)
)

(instance sCaneGetsBigGrant of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= theScript self)
				(DoDisplay 4 myDisplayColor 205 16)
				(= seconds 5)
			)
			(1
				(= theScript 0)
				(DrawPic 220 SCROLLLEFT)
				(= cycles 2)
			)
			(2
				(Say President_of_C_A_N_E_ 205 17 #dispose #caller self)
			)
			(3 (= ticks 45))
			(4
				(Say President_of_C_A_N_E_ 205 18 #dispose #caller self)
			)
			(5 (= ticks 45))
			(6
				(Say Luigi 205 19 #dispose #caller self)
			)
			(7 (= ticks 45))
			(8
				(Say Luigi 205 20 #dispose #caller self)
			)
			(9 (= ticks 45))
			(10
				(Say President_of_C_A_N_E_ 205 21 #dispose #caller self)
			)
			(11 (= ticks 45))
			(12
				(Say Luigi 205 22 #dispose #caller self)
			)
			(13 (= ticks 30))
			(14 (self dispose:))
		)
	)
)

(instance sCaneWorksCongress of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= theScript self)
				(DoDisplay 4 myDisplayColor 205 23)
				(= seconds 7)
			)
			(1
				(= theScript 0)
				(DrawPic 225 SCROLLLEFT)
				(= cycles 2)
			)
			(2
				(face init:)
				(arm init: setCycle: EndLoop self)
			)
			(3
				(TimePrint 205 24
					#title {Your Elected Official}
					#at 100 185
					#width 220
				)
				(= ticks 45)
			)
			(4
				(TimePrint 205 25
					#title {Your Elected Official}
					#at 100 185
					#width 220
				)
				(= ticks 45)
			)
			(5
				(TimePrint 205 26
					#title {Your Elected Official}
					#at 100 185
					#width 220
				)
				(= ticks 45)
			)
			(6
				(Say A_C_A_N_E_-er 205 27 #dispose #caller self)
			)
			(7 (= ticks 45))
			(8
				(Say A_C_A_N_E_-er 205 28 #dispose #caller self)
			)
			(9 (= ticks 45))
			(10
				(TimePrint 205 29
					#title {Your Elected Official}
					#at 100 185
					#width 220
				)
				(= ticks 30)
			)
			(11 (self dispose:))
		)
	)
)

(instance leftEyebrow of Prop
	(properties
		x 213
		y 57
		view 124
		priority 14
		signal (| ignrAct fixPriOn)
	)
)

(instance rightEyebrow of Prop
	(properties
		x 227
		y 57
		view 124
		loop 1
		priority 14
		signal (| ignrAct fixPriOn)
	)
)

(instance cigar of Prop
	(properties
		x 168
		y 87
		view 124
		loop 3
		priority 14
		signal (| ignrAct fixPriOn)
	)
)

(instance hand of Prop
	(properties
		x 154
		y 149
		view 124
		loop 4
		priority 14
		signal (| ignrAct fixPriOn)
	)
)

(instance face of Prop
	(properties
		x 221
		y 114
		view 227
		priority 14
		signal (| ignrAct fixPriOn)
	)
)

(instance arm of Prop
	(properties
		x 155
		y 110
		view 225
		priority 14
		signal (| ignrAct fixPriOn)
	)
)

(instance Vinnie of Talker
	(properties
		x 1
		y 180
		nsTop 66
		nsLeft 67
		view 1122
		loop 3
		talkWidth 250
	)
	
	(method (init)
		(= mouth mobFlunky1Mouth)
		(super init: &rest)
	)
)

(instance mobFlunky1Mouth of Prop
	(properties
		nsTop 31
		nsLeft 23
		view 1122
	)
)

(instance Bruno of Talker
	(properties
		x 100
		y 180
		nsTop 95
		nsLeft 267
		view 1123
		loop 3
	)
	
	(method (init)
		(= mouth mobFlunky2Mouth)
		(super init: &rest)
	)
)

(instance mobFlunky2Mouth of Prop
	(properties
		view 1123
	)
)

(instance Silas_Scruemall of Talker
	(properties
		x 80
		y 150
		nsTop 48
		nsLeft 173
		view 1215
		loop 3
		talkWidth 160
		name "Silas Scruemall"
	)
	
	(method (init)
		(= bust silasBust)
		(= eyes silasEyes)
		(= mouth silasMouth)
		(super init: &rest)
	)
)

(instance silasBust of View
	(properties
		view 1215
		loop 1
	)
)

(instance silasEyes of Prop
	(properties
		nsTop 1
		nsLeft -2
		view 1215
		loop 2
	)
)

(instance silasMouth of Prop
	(properties
		nsTop 20
		view 1215
	)
)

(instance President_of_C_A_N_E_ of Talker
	(properties
		x 120
		y 160
		nsTop 55
		nsLeft 192
		view 1220
		loop 3
		name "President of C.A.N.E."
	)
	
	(method (init)
		(= bust presBust)
		(= eyes presEyes)
		(= mouth presMouth)
		(super init: &rest)
	)
)

(instance presBust of View
	(properties
		view 1220
		loop 1
	)
)

(instance presEyes of Prop
	(properties
		nsTop 18
		nsLeft 17
		view 1220
		loop 2
	)
)

(instance presMouth of Prop
	(properties
		nsTop 35
		view 1220
	)
)

(instance Luigi of Talker
	(properties
		x 1
		y 160
		nsTop 77
		nsLeft 95
		view 1221
		loop 3
		talkWidth 200
	)
	
	(method (init)
		(= eyes flunkyEyes)
		(= mouth flunkyMouth)
		(super init: &rest)
	)
)

(instance flunkyEyes of Prop
	(properties
		nsTop -7
		nsLeft 1
		view 1221
		loop 2
	)
)

(instance flunkyMouth of Prop
	(properties
		view 1221
	)
)

(instance A_C_A_N_E_-er of Talker
	(properties
		x 1
		y 140
		nsTop 95
		nsLeft 144
		view 1225
		loop 3
		talkWidth 250
		name "A C.A.N.E.-er"
	)
	
	(method (init)
		(= eyes congressEyes)
		(= mouth congressMouth)
		(super init: &rest)
	)
)

(instance congressEyes of Prop
	(properties
		nsTop -10
		nsLeft -61
		view 1225
		loop 2
	)
)

(instance congressMouth of Prop
	(properties
		view 1225
	)
)
