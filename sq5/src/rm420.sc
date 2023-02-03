;;; Sierra Script 1.0 - (do not remove this comment)
(script# 420)
(include sci.sh)
(use Main)
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
	rm420 0
	sUseComm 20
)

(instance theMusic3 of Sound
	(properties)
)

(instance theMusic4 of Sound
	(properties)
)

(instance theMusic5 of Sound
	(properties)
)

(instance rm420 of Rm
	(properties
		noun 9
		picture 82
		style $000a
	)
	
	(method (init)
		(LoadMany 128 453)
		(curRoom
			addObstacle:
				((Polygon new:)
					type: 3
					init:
						221
						134
						164
						130
						158
						135
						223
						140
						226
						152
						144
						152
						36
						139
						62
						155
						107
						153
						115
						161
						103
						172
						65
						177
						110
						181
						121
						179
						146
						161
						206
						158
						239
						157
						242
						169
						259
						167
						259
						158
						242
						132
						236
						126
						226
						126
					yourself:
				)
		)
		(theComputer init:)
		(theFern init:)
		(theHumidifiers init: setOnMeCheck: 1 4)
		(thePlants init: setOnMeCheck: 1 2)
		(walkHandler addToFront: theComputer)
		(ego view: 0 init:)
		(switch prevRoomNum
			(410
				(NormalEgo 0)
				(ego
					init:
					setScale: Scaler 126 61 179 130
					loop: 0
					x: 10
					y: 153
				)
				(curRoom setScript: sRogEnter)
			)
			(440
				(ego init: view: 454 setLoop: 2 cel: 0)
				(curRoom setScript: sRogSaved)
			)
			(450
				(curRoom setScript: sInitRoom)
			)
			(460
				(curRoom setScript: sInitRoom)
			)
			(else 
				(curRoom setScript: sPukoidAttack)
			)
		)
		(super init:)
	)
	
	(method (doit)
		(super doit:)
		(if
		(and (SteppedOn ego 8) (not (curRoom script?)))
			(curRoom newRoom: 410)
		)
		(if
			(and
				(SteppedOn ego 32)
				(not (curRoom script?))
			)
			(Bset 101)
			(curRoom newRoom: 410)
		)
	)
	
	(method (dispose)
		(walkHandler delete: theComputer)
		(super dispose: &rest)
	)
)

(instance sInitRoom of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(plastic init: setScript: sPlastic)
				(plastic1 init:)
				(plastic2 init:)
				(branch1 init:)
				(branch2 init:)
				(drip init: setScript: sDrip1)
				(dripa init: hide:)
				(drip2 init:)
				(drip2a init: hide:)
				(drip2f init:)
				(if (Btst 98)
					(paper init: x: 117 y: 178 setCycle: 0 cel: 7 setPri: 11)
				)
				(if (Btst 77) (pukoidFeet init:) (flies init:))
				(switch prevRoomNum
					(410
						(if (not (Btst 90)) (shadow init: setScript: sShadow))
					)
					(440
						(theMusic1 number: 30 setLoop: -1 play:)
						(droole init: view: 455 setLoop: 0 cel: 0)
						(ego
							init:
							view: 454
							setLoop: 2
							cel: 0
							x: 229
							y: 159
							setScale: 0
						)
					)
					(450
						(NormalEgo 0)
						(ego
							init:
							setScale: Scaler 126 61 179 130
							loop: 5
							x: 230
							y: 155
						)
					)
					(460
						(NormalEgo 0)
						(ego
							init:
							setScale: Scaler 126 61 179 130
							loop: 5
							x: 209
							y: 155
						)
					)
					(else 
						(droole init: view: 455 setLoop: 0 cel: 0)
						(pukoidFeet init:)
						(ego
							init:
							view: 454
							setLoop: 2
							cel: 0
							x: 230
							y: 155
							setScale: 0
						)
					)
				)
				(= cycles 1)
			)
			(1
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sRogEnter of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(self setScript: sInitRoom self)
			)
			(1
				(theGame handsOff:)
				(ego setMotion: MoveTo 93 153 self)
			)
			(2
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sUseComm of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego view: 14 setLoop: 0 cel: 0 setCycle: End self)
				(theMusic2 number: 603 loop: 1 play:)
			)
			(1
				(messager say: 2 32 0 0 self 401)
			)
			(2 (ego setCycle: Beg self))
			(3
				(NormalEgo 0)
				(ego loop: 2)
				(= seconds 1)
			)
			(4
				(ego view: 6 setLoop: 0 cel: 15 setCycle: Beg self)
				(theMusic2 number: 260 setLoop: 1 play:)
			)
			(5
				(theMusic1 fade:)
				(curRoom newRoom: 240)
			)
		)
	)
)

(instance sRogSaved of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(self setScript: sInitRoom self)
			)
			(1
				(theGame handsOff:)
				(SolvePuzzle 219 50)
				(= seconds 2)
			)
			(2 (droole setCycle: End self))
			(3 (= seconds 1))
			(4
				(droole setLoop: 1 setCycle: End self)
			)
			(5
				(droole
					view: 30
					loop: 2
					cel: 0
					setScale: Scaler 126 61 179 130
					setCycle: Walk
					setLoop: Grooper
				)
				(ego setCycle: End self)
			)
			(6
				(NormalEgo 0)
				(ego
					init:
					setScale: Scaler 126 61 179 130
					loop: 5
					x: 230
					y: 155
				)
				(= cycles 3)
			)
			(7 (messager say: 3 0 0 0 self))
			(8
				(droole loop: 3 setMotion: MoveTo 230 127 self)
			)
			(9
				(droole loop: 0 setMotion: MoveTo 252 124 self)
			)
			(10
				(droole dispose:)
				(= cycles 3)
			)
			(11
				(messager say: 8 0 0 0 self)
			)
			(12
				(Bclr 97)
				(curRoom newRoom: 450)
			)
		)
	)
)

(instance sExit of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego setMotion: PolyPath 90 162 self)
			)
			(1 (curRoom newRoom: 410))
		)
	)
)

(instance sComputer of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego setMotion: PolyPath 209 155 self)
			)
			(1
				(if (not (Btst 77))
					(Bset 77)
					(self setScript: sPukoidAttack self)
				else
					(= cycles 1)
				)
			)
			(2
				(if (Btst 77)
					(curRoom newRoom: 440)
				else
					(theGame handsOn:)
					(self dispose:)
				)
			)
		)
	)
)

(instance sGotoComputer of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego setMotion: PolyPath 209 155 self)
			)
			(1 (ego loop: 2) (= cycles 2))
			(2 (curRoom newRoom: 460))
		)
	)
)

(instance sPukoidAttack of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(paper init: hide:)
				(pukoid init: setCycle: End self)
				(theMusic1 number: 35 setLoop: -1 play:)
			)
			(1
				(pukoid
					setStep: 8 2
					setLoop: 1
					cel: 0
					x: 81
					y: 179
					setCycle: Fwd
					setMotion: MoveTo 117 172 self
				)
			)
			(2
				(pukoid setStep: 15 5 setMotion: MoveTo 168 155 self)
				(paper setScript: sPaper)
			)
			(3
				(pukoid setLoop: 2 cel: 0 x: 169 y: 157 setCycle: 0)
				(= cycles 1)
			)
			(4
				(ego
					view: 454
					setLoop: 0
					setCel: 0
					setScale: 0
					setCycle: 0
					x: 229
					y: 159
					setCycle: End self
				)
			)
			(5 (= seconds 2))
			(6
				(pukoid
					setLoop: 2
					cel: 0
					x: 169
					y: 157
					setCycle: CT 3 1 self
				)
			)
			(7
				(theMusic4 number: 5042 loop: 1 play:)
				(ego view: 454 setLoop: 1 cel: 0 setCycle: End)
				(pukoid setCycle: End self)
			)
			(8
				(theMusic4 number: 136 loop: 1 play:)
				(= cycles 1)
			)
			(9 (pukoid setCycle: Beg self))
			(10
				(pukoid setLoop: 5 cel: 0 setCycle: End self)
			)
			(11 (self dispose:))
		)
	)
)

(instance sGetPaper of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego setMotion: PolyPath 130 172 self)
			)
			(1
				(SolvePuzzle 220 5)
				(ego view: 19 setLoop: 1 cel: 0 setCycle: End self)
			)
			(2
				(ego get: 22 setCycle: Beg self)
				(Bclr 98)
				(paper hide: dispose:)
			)
			(3
				(NormalEgo 0)
				(ego
					init:
					setScale: Scaler 126 61 179 130
					loop: 1
					x: 130
					y: 172
				)
				(= cycles 3)
			)
			(4
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sPaper of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(paper show: setMotion: MoveTo 117 178 self)
			)
			(1
				(paper setCycle: 0 setCel: 7 setPri: 11)
				(= cycles 1)
			)
			(2 (Bset 98) (self dispose:))
		)
	)
)

(instance sPlastic of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if
				(< (theGame detailLevel:) (plastic2 detailLevel:))
					(-- state)
				)
				(= seconds (Random 1 3))
			)
			(1
				(plastic2 setCycle: End self)
			)
			(2 (= seconds 1))
			(3
				(plastic1 setCycle: End self)
				(plastic setCycle: End self)
				(branch1 setCycle: End self)
				(branch2 setCycle: End self)
			)
			(4 0)
			(5 0)
			(6 0)
			(7 (= seconds (Random 2 8)))
			(8
				(plastic1 setCycle: Beg self)
				(plastic setCycle: Beg self)
				(branch1 setCycle: Beg self)
				(branch2 setCycle: Beg self)
			)
			(9 0)
			(10 0)
			(11 0)
			(12 (= seconds 1))
			(13
				(plastic2 setCycle: Beg self)
			)
			(14 (= state -1) (= cycles 2))
		)
	)
)

(instance sDrip1 of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= seconds 2))
			(1
				(drip init: setCycle: End self)
			)
			(2
				(drip cel: 0)
				(dripa x: 206 y: 43 show: setMotion: MoveTo 206 165 self)
			)
			(3
				(dripa hide:)
				(dripe init: show: setCycle: End self)
				(theMusic3 number: 137 loop: 1 play:)
			)
			(4
				(dripe cel: 0 hide:)
				(= seconds (Random 1 4))
			)
			(5
				(drip2 init: setCycle: End self)
			)
			(6
				(drip2 cel: 0)
				(drip2a x: 152 y: 7 show: setMotion: MoveTo 149 175 self)
			)
			(7
				(drip2a hide:)
				(drip2f init: setCycle: End self)
				(theMusic3 number: 137 loop: 1 play:)
			)
			(8
				(drip2f cel: 0)
				(= state -1)
				(= cycles 2)
			)
		)
	)
)

(instance sShadow of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= seconds 4))
			(1
				(Bset 90)
				(shadow setStep: 2 1 setMotion: MoveTo 330 127)
			)
			(2 (self dispose:))
		)
	)
)

(instance pukoid of Actor
	(properties
		x 56
		y 179
		view 457
	)
	
	(method (init)
		(super init: &rest)
		(self setLoop: 0)
	)
)

(instance pukoidFeet of View
	(properties
		x 214
		y 171
		noun 11
		view 457
		loop 4
		priority 8
	)
	
	(method (init)
		(super init: &rest)
		(self setLoop: 4 cel: 0 setPri: 8)
	)
)

(instance roger of Actor
	(properties
		x 229
		y 159
		view 454
		cel 1
	)
)

(instance shadow of Actor
	(properties
		x 277
		y 127
		view 453
		loop 8
	)
	
	(method (init)
		(super init: &rest)
		(self setLoop: 8 setCel: 0)
	)
)

(instance paper of Actor
	(properties
		x 118
		y 130
		noun 12
		approachX 120
		approachY 120
		view 457
		loop 3
	)
	
	(method (init)
		(super init: &rest)
		(self setLoop: 3 setCycle: Fwd)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(4
				(if (Btst 98) (curRoom setScript: sGetPaper))
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance droole of Actor
	(properties
		x 228
		y 138
		view 455
	)
)

(instance plastic of Prop
	(properties
		x 86
		y 89
		view 453
		detailLevel 2
	)
	
	(method (init)
		(super init: &rest)
		(self setLoop: 0 cel: 0)
	)
)

(instance plastic1 of Prop
	(properties
		x 85
		view 453
		loop 1
		detailLevel 2
	)
	
	(method (init)
		(super init: &rest)
		(self setLoop: 1 cel: 0)
	)
)

(instance plastic2 of Prop
	(properties
		x 247
		view 453
		loop 2
		detailLevel 2
	)
	
	(method (init)
		(super init: &rest)
		(self setLoop: 2 cel: 0)
	)
)

(instance branch1 of Prop
	(properties
		x 34
		y 56
		view 453
		loop 3
		detailLevel 2
	)
	
	(method (init)
		(super init: &rest)
		(self setLoop: 3 cel: 0)
	)
)

(instance branch2 of Prop
	(properties
		x 47
		y 84
		view 453
		loop 4
		detailLevel 2
	)
	
	(method (init)
		(super init: &rest)
		(self setLoop: 4 cel: 0)
	)
)

(instance drip of Prop
	(properties
		x 206
		y 43
		view 453
		loop 5
	)
	
	(method (init)
		(super init: &rest)
		(self setLoop: 5 cel: 0)
	)
)

(instance dripa of Actor
	(properties
		x 206
		y 45
		view 453
		loop 7
		signal $4000
	)
	
	(method (init)
		(super init: &rest)
		(self setLoop: 7 setCel: 0 setStep: 3 10)
	)
)

(instance dripe of Prop
	(properties
		x 206
		y 165
		view 453
		loop 6
		priority 12
		signal $4000
	)
	
	(method (init)
		(super init: &rest)
		(self setLoop: 6 cel: 0 setPri: 12)
	)
)

(instance drip2 of Prop
	(properties
		x 152
		y 7
		view 453
		loop 5
	)
	
	(method (init)
		(super init: &rest)
		(self setLoop: 5 cel: 0)
	)
)

(instance drip2a of Actor
	(properties
		x 152
		y 22
		view 453
		loop 7
		signal $4000
	)
	
	(method (init)
		(super init: &rest)
		(self setLoop: 7 setCel: 0 setStep: 3 10)
	)
)

(instance drip2f of Prop
	(properties
		x 149
		y 175
		view 453
		loop 6
	)
	
	(method (init)
		(super init: &rest)
		(self setLoop: 6 cel: 0)
	)
)

(instance flies of Prop
	(properties
		x 168
		y 115
		noun 5
		view 457
		loop 6
		signal $4000
	)
	
	(method (init)
		(super init: &rest)
		(self setCycle: Fwd)
	)
)

(instance theComputer of Feature
	(properties
		x 152
		y 125
		noun 1
		nsTop 125
		nsLeft 152
		nsBottom 165
		nsRight 208
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(4
				(if (not (Btst 77))
					(curRoom setScript: sComputer)
				else
					(curRoom setScript: sGotoComputer)
				)
			)
			(3
				(if (not (Btst 77))
					(curRoom setScript: sComputer)
				else
					(curRoom setScript: sGotoComputer)
				)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance theFern of Feature
	(properties
		x 10
		y 28
		noun 4
		nsTop 28
		nsBottom 122
		nsRight 48
	)
)

(instance theHumidifiers of Feature
	(properties
		x 160
		y 28
		noun 6
		onMeCheck $0004
	)
)

(instance thePlants of Feature
	(properties
		x 320
		y 35
		noun 7
		onMeCheck $0002
	)
)
