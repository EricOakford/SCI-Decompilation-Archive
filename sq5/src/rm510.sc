;;; Sierra Script 1.0 - (do not remove this comment)
(script# 510)
(include sci.sh)
(use Main)
(use Talker)
(use Scaler)
(use PolyPath)
(use Polygon)
(use Feature)
(use LoadMany)
(use Grooper)
(use Sound)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	rm510 0
	genTalker 1
)

(local
	local0
	local1
)
(instance theMusic3 of Sound
	(properties)
)

(instance theMusic4 of Sound
	(properties)
)

(instance rm510 of Rm
	(properties
		noun 5
		picture 70
		style $000a
		vanishingY 50
	)
	
	(method (init)
		(LoadMany 128 542)
		(= local1 (DoSound sndGET_AUDIO_CAPABILITY))
		(curRoom
			addObstacle:
				((Polygon new:)
					type: 3
					init:
						29
						159
						13
						175
						216
						175
						241
						175
						251
						175
						241
						171
						203
						171
						180
						169
						156
						129
						217
						129
						194
						111
						165
						111
						82
						159
					yourself:
				)
		)
		(curRoom setRegions: 505)
		(theConsole init: setOnMeCheck: 1 4)
		(theCellLFront init: setOnMeCheck: 1 8)
		(theCliffyCell init: setOnMeCheck: 1 16)
		(theCellRFront init: setOnMeCheck: 1 32)
		(theCellRBack init: setOnMeCheck: 1 64)
		(if (not (Btst 55))
			(forceField init:)
			(walkHandler addToFront: forceField)
		)
		(ego
			view: 0
			init:
			setScale: Scaler 107 30 164 115
			setLoop: 0
		)
		(switch prevRoomNum
			(500
				(NormalEgo 0)
				(ego
					setScale: Scaler 107 30 164 115
					posn: 20 162
					illegalBits: 0
				)
				(theGame handsOff:)
				(curRoom setScript: sFromBar)
			)
			(530
				(if (not (Btst 49))
					(theGame handsOff:)
					(curRoom setScript: sCliffyOut)
				else
					(curRoom setScript: sRogByCell)
				)
			)
			(else 
				(Bset 48)
				(Bset 49)
				(NormalEgo 0)
				(ego setScale: Scaler 107 30 164 115 posn: 20 162)
				(theGame handsOff:)
				(curRoom setScript: sFromBar)
			)
		)
		(super init: &rest)
	)
	
	(method (doit)
		(super doit:)
		(if
		(and (SteppedOn ego 2) (not (curRoom script?)))
			(curRoom newRoom: 500)
		)
	)
	
	(method (dispose)
		(walkHandler delete: forceField)
		(super dispose: &rest)
	)
)

(instance sRogByCell of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(cellCover init:)
				(self setScript: sInitLites self)
				(forceField dispose:)
				(walkHandler delete: forceField)
				(theGame handsOn:)
				(NormalEgo 0)
				(ego loop: 1 x: 162 y: 117)
				(= cycles 1)
			)
			(1 (self dispose:))
		)
	)
)

(instance sCliffyOut of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(self setScript: sInitLites)
				(theGame handsOff:)
				(Bset 55)
				(forceField dispose:)
				(walkHandler delete: forceField)
				(NormalEgo 4)
				(ego x: 165 y: 140)
				(= cycles 2)
			)
			(1
				(ego setMotion: MoveTo 134 162 self)
				(cliffy
					init:
					view: 542
					setLoop: 3
					cel: 0
					x: 157
					y: 117
					setPri: 5
					setCycle: End
				)
			)
			(2
				(ego setMotion: MoveTo -20 162 self)
				(cliffy
					view: 20
					setLoop: 2
					posn: 157 117
					setPri: 12
					setScale: Scaler 107 35 164 115
					setCycle: Fwd
					setLoop: Grooper
					setStep: 1 1
					moveSpeed: 20
					setMotion: MoveTo 150 125 self
				)
			)
			(3 0)
			(4
				(Bclr 49)
				(Bset 54)
				(= global124 12)
				(curRoom newRoom: 500)
			)
		)
	)
)

(instance sFromBar of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theMusic1 number: 36 loop: -1 play:)
				(theGame handsOff:)
				(cellCover init:)
				(if (Btst 54)
					(theMusic2 number: 105 loop: -1 play:)
					(self setScript: sInitLites self)
				else
					(cLite1 init: setCycle: Fwd cycleSpeed: 20)
					(cLite2 init: setCycle: Fwd cycleSpeed: 20)
					(controlp init: setCycle: Fwd cycleSpeed: 10)
					(guard1 init:)
					(head2 init:)
					(guard2 init: setScript: sAmbientGuards)
					(= cycles 1)
				)
			)
			(1
				(ego setMotion: MoveTo 90 162 self)
			)
			(2
				(if (not (Btst 54))
					(guard1 setCycle: End self)
				else
					(= cycles 1)
				)
			)
			(3
				(if (and (not (Btst 54)) (not (Btst 48)))
					(messager say: 10 0 0 (Random 1 3) self)
				else
					(= cycles 1)
				)
			)
			(4
				(if (not (Btst 54))
					(guard1 setCycle: Beg self)
				else
					(= cycles 1)
				)
			)
			(5
				(if (and (Btst 48) (not (Btst 54)))
					(Bset 54)
					(= next sAlarm)
				else
					(theGame handsOn:)
				)
				(self dispose:)
			)
		)
	)
)

(instance sInitLites of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theMusic2 number: 105 loop: -1 play:)
				(lite1 init: setCycle: Fwd cycleSpeed: 20)
				(lite2 init: setCycle: Fwd cycleSpeed: 20)
				(lite3 init: setCycle: Fwd cycleSpeed: 20)
				(lite4 init: setCycle: Fwd cycleSpeed: 20)
				(lite5 init: setCycle: Fwd cycleSpeed: 20)
				(lite6 init: setCycle: Fwd cycleSpeed: 20)
				(controlp init: setCycle: Fwd cycleSpeed: 20)
				(cLite1 init: setCycle: Fwd cycleSpeed: 20)
				(cLite2 init: setCycle: Fwd cycleSpeed: 20)
				(= cycles 3)
			)
			(1 (self dispose:))
		)
	)
)

(instance sAlarm of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(messager say: 2 0 0 0 self)
			)
			(1
				(theMusic2 number: 105 loop: -1 play:)
				(lite1 init: setCycle: Fwd cycleSpeed: 20)
				(lite2 init: setCycle: Fwd cycleSpeed: 20)
				(lite3 init: setCycle: Fwd cycleSpeed: 20)
				(lite4 init: setCycle: Fwd cycleSpeed: 20)
				(lite5 init: setCycle: Fwd cycleSpeed: 20)
				(lite6 init: setCycle: Fwd cycleSpeed: 20)
				(controlp
					init:
					setLoop: 11
					cel: 1
					setCycle: Fwd
					cycleSpeed: 20
				)
				(= seconds 4)
			)
			(2
				(head2 hide: dispose:)
				(guard1
					view: 540
					setLoop: 1
					cel: 0
					x: 194
					y: 167
					setCycle: End
				)
				(guard2
					view: 541
					setLoop: 2
					cel: 0
					x: 228
					y: 180
					cycleSpeed: 6
					setPri: 11
					setScript: 0
					setCycle: End
				)
				(= seconds 1)
			)
			(3 (messager say: 1 0 0 0 self))
			(4
				(guard1
					view: 540
					setLoop: 2
					cel: 0
					x: 187
					y: 168
					setCycle: End self
				)
				(guard2
					view: 541
					setLoop: 3
					cel: 0
					x: 217
					y: 182
					setCycle: End self
				)
			)
			(5
				(guard1
					view: 540
					setLoop: 3
					cel: 0
					x: 188
					y: 167
					setStep: 8 2
					setCycle: Walk
					setMotion: MoveTo -20 167 self
				)
				(guard2
					view: 541
					setLoop: 4
					cel: 8
					x: 221
					y: 185
					setStep: 8 2
					setPri: 11
					setCycle: Walk
					setMotion: MoveTo -20 185 self
				)
			)
			(6 0)
			(7
				(guard1 dispose:)
				(guard2 dispose:)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sForceField of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego setMotion: MoveTo 118 162 self)
			)
			(1
				(ego
					view: 542
					setLoop: 0
					setCycle: 0
					setCel: 0
					x: 112
					y: 162
					setPri: 12
				)
				(theMusic3 number: 516 loop: 1 play:)
				(= cycles 5)
			)
			(2
				(ego view: 542 setLoop: 0 cel: 0 setCycle: End self)
			)
			(3
				(NormalEgo 0)
				(ego x: 112 y: 160)
				(= cycles 3)
			)
			(4
				(if local1
					(theMusic4 number: 102 setLoop: 1 play: self)
				else
					(= cycles 1)
				)
			)
			(5
				(cond 
					((== 0 local1)
						(if (Btst 54)
							(messager say: 11 0 3 (Random 1 3) self)
						else
							(switch global166
								(0
									(= global166 1)
									(messager say: 11 0 1 0 self)
								)
								(1
									(= global166 2)
									(messager say: 11 0 2 0 self)
								)
								(2
									(messager say: 11 0 3 (Random 1 3) self)
								)
								(else  (= cycles 1))
							)
						)
					)
					((not (Btst 54))
						(switch global166
							(0
								(= global166 1)
								(messager say: 11 0 1 2 3 self)
							)
							(1
								(= global166 2)
								(messager say: 11 0 2 2 3 self)
							)
							(else  (= cycles 1))
						)
					)
					(else (= cycles 2))
				)
			)
			(6
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sAmbientGuards of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= seconds (Random 1 6)))
			(1 (head2 setCycle: End self))
			(2 (= seconds (Random 1 5)))
			(3 (head2 setCycle: Beg self))
			(4 (= state -1) (= cycles 1))
		)
	)
)

(instance sConsole of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego setMotion: MoveTo 169 157 self)
			)
			(1
				(ego
					view: 542
					setLoop: 13
					cel: 0
					x: 169
					y: 157
					setPri: 14
					setCycle: End self
				)
			)
			(2
				(SolvePuzzle 216 25)
				(= seconds 2)
			)
			(3
				(Bset 55)
				(forceField dispose:)
				(walkHandler delete: forceField)
				(theGame handsOn:)
				(ego init:)
				(NormalEgo 0)
				(self dispose:)
			)
		)
	)
)

(instance sCellRFront of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego setMotion: PolyPath 192 130 self)
			)
			(1
				(ego
					view: 542
					setLoop: 1
					cel: 0
					x: 192
					y: 130
					setCycle: End self
				)
			)
			(2 (messager say: 6 1 0 0 self))
			(3
				(= local0 0)
				(theGame handsOn:)
				(NormalEgo 0)
				(self dispose:)
			)
		)
	)
)

(instance sCellLFront of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego setMotion: PolyPath 152 126 self)
			)
			(1
				(ego
					view: 542
					setLoop: 2
					cel: 0
					x: 152
					y: 126
					setCycle: End self
				)
			)
			(2 (messager say: 6 1 0 0 self))
			(3
				(= local0 0)
				(theGame handsOn:)
				(NormalEgo 0)
				(self dispose:)
			)
		)
	)
)

(instance sCellRBack of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego setMotion: PolyPath 195 120 self)
			)
			(1
				(ego
					view: 542
					setLoop: 1
					cel: 0
					x: 195
					y: 120
					setCycle: End self
				)
			)
			(2 (messager say: 6 1 0 0 self))
			(3
				(= local0 0)
				(theGame handsOn:)
				(NormalEgo 0)
				(self dispose:)
			)
		)
	)
)

(instance sCliffyCell of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego setMotion: PolyPath 162 117 self)
			)
			(1
				(ego
					view: 542
					setLoop: 2
					cel: 0
					x: 162
					y: 117
					setCycle: End self
				)
			)
			(2
				(ego hide:)
				(curRoom newRoom: 530)
			)
		)
	)
)

(instance cliffy of Actor
	(properties
		x 157
		y 117
		view 542
		loop 3
		priority 12
	)
)

(instance guard1 of Actor
	(properties
		x 194
		y 167
		noun 8
		view 540
		signal $4000
		illegalBits $0000
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(2
				(messager say: 8 2 0 (Random 1 3))
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance guard2 of Actor
	(properties
		x 228
		y 180
		z 20
		noun 9
		view 541
		cel 1
		signal $4000
		illegalBits $0000
	)
	
	(method (init)
		(if (not (Btst 54))
			(self setPri: 11 setCycle: Fwd cycleSpeed: 30)
			(super init: &rest)
		)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(2
				(messager say: 9 2 0 (Random 1 3))
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance head2 of Prop
	(properties
		x 231
		y 114
		view 541
		loop 1
		signal $4000
	)
)

(instance lite1 of Prop
	(properties
		x 155
		y 51
		view 542
		loop 6
		cel 2
	)
)

(instance lite2 of Prop
	(properties
		x 137
		y 24
		view 542
		loop 5
		cel 2
		signal $4000
	)
)

(instance lite3 of Prop
	(properties
		x 165
		y 68
		view 542
		loop 7
		cel 2
	)
)

(instance lite4 of Prop
	(properties
		x 183
		y 27
		view 542
		loop 8
		cel 2
	)
)

(instance lite5 of Prop
	(properties
		x 184
		y 51
		view 542
		loop 9
		cel 2
	)
)

(instance lite6 of Prop
	(properties
		x 185
		y 68
		view 542
		loop 10
		cel 2
	)
)

(instance controlp of Prop
	(properties
		x 187
		y 117
		view 542
		loop 11
		cel 1
		priority 12
		signal $4010
	)
	
	(method (init)
		(super init: &rest)
		(if (not (Btst 54)) (self setLoop: 12 cel: 0))
	)
)

(instance cLite1 of Prop
	(properties
		x 194
		y 129
		view 542
		loop 14
		cel 1
		priority 12
		signal $4010
	)
)

(instance cLite2 of Prop
	(properties
		x 205
		y 128
		view 542
		loop 15
		priority 12
		signal $4010
	)
)

(instance cellCover of View
	(properties
		x 142
		y 103
		view 542
		loop 4
		priority 6
		signal $0010
	)
)

(instance genTalker of Narrator
	(properties
		talkWidth 80
	)
	
	(method (init)
		(= font userFont)
		(= systemWindow theSpeakWindow)
		(switch curTalker
			(42
				(= talkWidth (FindLanguage 130 90 90 90 100))
				(systemWindow
					tailX: (- (guard1 x?) 30)
					tailY: (- (guard1 y?) 70)
					xOffset: -20
					isBottom: 1
				)
			)
			(43
				(= talkWidth (FindLanguage 130 90 90 90 100))
				(systemWindow
					tailX: (- (guard2 x?) 10)
					tailY: (- (guard2 y?) 90)
					xOffset: 0
					isBottom: 1
				)
			)
			(19
				(= talkWidth (FindLanguage 80 90 90 90 80))
				(systemWindow
					tailX: (ego x?)
					tailY: (- (ego y?) 65)
					xOffset: 0
					isBottom: 1
				)
			)
		)
		(super init: &rest)
	)
	
	(method (dispose)
		(= systemWindow gSq5Win_2)
		(super dispose: &rest)
	)
)

(instance forceField of Feature
	(properties
		x 118
		y 162
		noun 5
		nsLeft 105
		nsBottom 155
		nsRight 300
	)
	
	(method (init)
		(if (Btst 55)
			(self nsTop: 0 nsLeft: 0 nsRight: 0 nsBottom: 0 x: 0 y: 0)
		else
			(forceField approachVerbs: 3)
			(super init:)
		)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(3
				(if (not (Btst 55))
					(curRoom setScript: sForceField)
				else
					(super doVerb: theVerb &rest)
				)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance theConsole of Feature
	(properties
		x 200
		y 170
		noun 4
		onMeCheck $0004
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(4
				(if (and (not (Btst 55)) (Btst 54))
					(curRoom setScript: sConsole)
				)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance theCellLFront of Feature
	(properties
		x 136
		y 170
		noun 6
		onMeCheck $0008
	)
	
	(method (init)
		(super init:)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(4
				(if (Btst 55) (curRoom setScript: sCellLFront))
			)
			(3
				(if (Btst 55) (curRoom setScript: sCellLFront))
			)
			(1
				(if (Btst 55)
					(= local0 1)
					(curRoom setScript: sCellLFront)
				else
					(messager say: 6 1 0 0)
				)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance theCliffyCell of Feature
	(properties
		x 151
		y 170
		noun 3
		onMeCheck $0010
	)
	
	(method (init)
		(super init:)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(4
				(if (Btst 55) (curRoom setScript: sCliffyCell))
			)
			(3
				(if (Btst 55) (curRoom setScript: sCliffyCell))
			)
			(1
				(if (Btst 55)
					(curRoom setScript: sCliffyCell)
				else
					(messager say: 3 1 0 0)
				)
			)
			(31
				(if (Btst 55) (curRoom setScript: sCliffyCell))
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance theCellRFront of Feature
	(properties
		x 219
		y 170
		noun 6
		onMeCheck $0020
	)
	
	(method (init)
		(super init:)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(4
				(if (Btst 55) (curRoom setScript: sCellRFront))
			)
			(3
				(if (Btst 55) (curRoom setScript: sCellRFront))
			)
			(1
				(if (Btst 55)
					(= local0 1)
					(curRoom setScript: sCellRFront)
				else
					(messager say: 6 1 0 0)
				)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance theCellRBack of Feature
	(properties
		x 208
		y 110
		noun 6
		onMeCheck $0040
	)
	
	(method (init)
		(super init:)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(4
				(if (Btst 55) (curRoom setScript: sCellRBack))
			)
			(3
				(if (Btst 55) (curRoom setScript: sCellRBack))
			)
			(1
				(if (Btst 55)
					(= local0 1)
					(curRoom setScript: sCellRBack)
				else
					(messager say: 6 1 0 0)
				)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)
