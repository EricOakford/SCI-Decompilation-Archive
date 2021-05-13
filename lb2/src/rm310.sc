;;; Sierra Script 1.0 - (do not remove this comment)
(script# 310)
(include sci.sh)
(use Main)
(use LbDoor)
(use LBRoom)
(use ExitFeature)
(use Talker)
(use Scaler)
(use PolyPath)
(use Polygon)
(use Feature)
(use LoadMany)
(use Motion)
(use Actor)
(use System)

(public
	rm310 0
	Bartender 22
	PianoPlayer 30
	Singer 31
)

(local
	local0 =  1
	local1 =  1
	local2 =  1
	local3 =  1
	local4
)
(procedure (localproc_03f5 &tmp temp0)
	(switch (= temp0 (curRoom setInset: (ScriptID 20 0)))
		(1026 (messager say: 1 6 28))
		(1027 (messager say: 1 6 30))
		(259 (messager say: 1 6 17))
		(1029 (messager say: 1 6 12))
		(261 (messager say: 1 6 9))
		(780 (messager say: 1 6 20))
		(516 (messager say: 1 6 24))
		(1028
			((ScriptID 21 0) doit: 268)
			(messager say: 1 6 31)
		)
		(262 (messager say: 1 6 15))
		(518 (messager say: 1 6 35))
		(517 (messager say: 1 6 19))
		(519 (messager say: 1 6 29))
		(513 (messager say: 1 6 23))
		(260 (messager say: 1 6 13))
		(258 (messager say: 1 6 16))
		(514 (messager say: 1 6 22))
		(268 (messager say: 1 6 27))
		(520 (messager say: 1 6 35))
		(263 (messager say: 1 6 14))
		(264 (messager say: 1 6 3))
		(-1 0)
		(else 
			(cond 
				((and (<= 256 temp0) (<= temp0 409)) (messager say: 1 6 18))
				((and (<= 768 temp0) (<= temp0 921)) (messager say: 1 6 26))
				(else (messager say: 1 6 21))
			)
		)
	)
)

(instance rm310 of LBRoom
	(properties
		noun 13
		picture 310
		north 320
		south 300
		vanishingX 167
		vanishingY -20
	)
	
	(method (init &tmp [temp0 3] temp3 [temp4 30])
		(LoadMany 128 311 317 312 313 314 315 318 831 830)
		(LoadMany 132 310 311 312 314)
		(ego
			setScale: Scaler 137 0 190 -20
			init:
			normalize: (if (ego wearingGown?) 831 else 830)
		)
		(switch prevRoomNum
			(north
				(ego x: 236 y: 107 edgeHit: 0 setHeading: 180)
				(theMusic setVol: 127)
				(theMusic2 stop:)
			)
			(south
				(ego x: 195)
				(= temp3
					(switch (DoSound sndGET_POLYPHONY)
						(32 310)
						(6 315)
						(else  314)
					)
				)
			)
			(else 
				(ego posn: 160 160)
				(theGame handsOn:)
			)
		)
		(super init:)
		(LoadMany 128 311 317 312 313 314 315 318 831 830)
		(LoadMany 132 310 311 312 314)
		(if (== prevRoomNum south)
			(switch (Random 0 2)
				(0
					(WrapMusic init: 1 temp3 311 1312)
				)
				(1
					(WrapMusic init: 1 311 temp3 1312)
				)
				(2
					(WrapMusic init: 1 1312 temp3 311)
				)
			)
		)
		(self
			addObstacle:
				((Polygon new:)
					type: 2
					init:
						26
						189
						0
						189
						0
						0
						319
						0
						319
						189
						287
						189
						238
						172
						201
						118
						245
						111
						245
						97
						274
						92
						258
						69
						219
						108
						194
						115
						171
						127
						120
						127
						85
						152
					yourself:
				)
		)
		(bartender approachVerbs: 1 2 6 addToPic:)
		(barfly1 addToPic:)
		(barfly2 addToPic:)
		(ziggy approachVerbs: 1 2 6 init: setScript: sZiggySmokes)
		(woman2 addToPic:)
		(dancersA init: setScript: sRDancers)
		(dancersB init: setScript: sMDancers)
		(dancersC setCycle: Fwd init:)
		(flapper setCycle: Fwd init:)
		(pianoplayer init: setCycle: Fwd)
		(sleeper addToPic:)
		(woman1 addToPic:)
		(bathroomDoor init:)
		(endOfBar init:)
		(southExitFeature init:)
		((ScriptID 1881 2)
			x: 5
			y: 95
			textX: 120
			textY: 20
			talkWidth: 120
		)
	)
	
	(method (doit)
		(if (not local4) (theIconBar disable: 7))
		(super doit:)
	)
	
	(method (newRoom n)
		(= local4 1)
		(theIconBar enable: 7)
		(if (== n 320)
			(theMusic fade: 80 10 12 0)
		else
			(WrapMusic dispose:)
		)
		(super newRoom: n)
	)
)

(instance sRDancers of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(dancersA view: 313 loop: 0 setCycle: Fwd)
				(= seconds (Random 5 10))
			)
			(1
				(dancersA view: 313 loop: 1 setCycle: Fwd)
				(= seconds (Random 4 6))
			)
			(2 (= state -1) (= cycles 1))
		)
	)
)

(instance sMDancers of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(dancersB view: 313 loop: 2 setCycle: Fwd)
				(= seconds (Random 4 7))
			)
			(1
				(dancersB view: 314 loop: 2 setCycle: End self)
			)
			(2
				(dancersB view: 313 loop: 3 setCycle: Fwd)
				(= seconds (Random 4 8))
			)
			(3
				(dancersB view: 314 loop: 2 cel: 11 setCycle: Beg self)
			)
			(4 (= state -1) (= cycles 1))
		)
	)
)

(instance sBartender of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(bartender setLoop: 2)
				(= seconds (Random 9 19))
			)
			(1
				(switch (Random 0 1)
					(0
						(bartender setLoop: 1 setMotion: MoveTo 55 113 self)
					)
					(1
						(bartender setLoop: 0 setMotion: MoveTo 7 123 self)
					)
				)
			)
			(2
				(if (> (bartender x?) 10)
					(bartender setLoop: 0)
				else
					(bartender setLoop: 1)
				)
				(bartender setMotion: MoveTo 47 115 self)
			)
			(3 (= state -1) (= cycles 1))
		)
	)
)

(instance sZiggySmokes of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (ziggy setCycle: End self))
			(1 (= seconds (Random 1 6)))
			(2
				(switch (Random 0 2)
					(0 (= cycles 1))
					(else 
						(ziggy cel: 3 setCycle: End self)
					)
				)
			)
			(3
				(ziggy cel: 3 setCycle: CT 0 -1 self)
			)
			(4 (= seconds (Random 2 4)))
			(5 (= state -1) (= cycles 1))
		)
	)
)

(instance sNodder of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (woman2 setCycle: End self))
			(1 (= cycles (Random 1 10)))
			(2 (woman2 setCycle: Beg self))
			(3 (= cycles (Random 10 20)))
			(4 (= state -1) (= cycles 1))
		)
	)
)

(instance sWhoSentYa of Script
	(properties)
	
	(method (changeState newState &tmp temp0)
		(switch (= state newState)
			(0
				(messager say: 1 2 6 0 self)
				(= local1 0)
				((ScriptID 22 0) doit: 8)
				(theGame points: 1 131)
			)
			(1
				(switch (= temp0 (curRoom setInset: (ScriptID 20 0)))
					(261
						(messager say: 1 2 8 0 self)
						(Bset 118)
					)
					(else 
						(if (and (<= 256 temp0) (<= temp0 409))
							(messager say: 1 2 7 0 self)
						else
							(messager say: 1 6 21 0 self)
						)
						(Bclr 118)
					)
				)
			)
			(2 (self dispose:))
		)
	)
)

(instance sLeaveSouth of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(= cycles 1)
			)
			(1
				(ego setMotion: PolyPath mouseX 190 self)
			)
			(2
				(ego setMotion: MoveTo (ego x?) 270 self)
			)
			(3 (curRoom newRoom: 300))
		)
	)
)

(instance bartender of View
	(properties
		x 47
		y 115
		noun 2
		approachX 113
		approachY 129
		view 312
		loop 2
		signal $4000
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(6
				(switch (curRoom setInset: (ScriptID 20 0))
					(264 (messager say: 2 6 3 0))
					(-1 0)
					(else  (messager say: 2 6 4 0))
				)
			)
			(2 (messager say: 2 2 0 0))
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance barfly1 of View
	(properties
		x 59
		y 148
		noun 3
		view 312
		loop 3
		priority 10
		signal $4010
	)
)

(instance barfly2 of View
	(properties
		x 81
		y 140
		noun 10
		view 312
		loop 4
		priority 10
		signal $4010
	)
)

(instance ziggy of Prop
	(properties
		x 292
		y 124
		noun 1
		approachX 232
		approachY 157
		view 318
		cel 5
		priority 12
		signal $1010
	)
	
	(method (doVerb theVerb &tmp temp0)
		(switch theVerb
			(6
				(cond 
					((and (TriggerEvent 8 0) (Btst 118)) (localproc_03f5))
					(local1 (messager say: 1 2 33 2))
					((Btst 118) (localproc_03f5))
					(else
						(switch (curRoom setInset: (ScriptID 20 0))
							(261
								(messager say: 1 6 9 0)
								(Bset 118)
							)
							(else  (messager say: 1 6 21 0))
						)
					)
				)
			)
			(2
				(cond 
					((TriggerEvent 8 0) (messager say: 1 2 32))
					(local1 (curRoom setScript: sWhoSentYa))
					(local2
						(if (Btst 118)
							(messager say: 1 2 32)
						else
							(messager say: 1 2 33)
						)
						(= local2 0)
					)
					(local3
						(if (Btst 118)
							(messager say: 1 2 34)
						else
							(messager say: 1 2 33)
						)
					)
				)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance flapper of Prop
	(properties
		x 141
		y 87
		noun 4
		view 315
		cel 1
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(6
				(switch (curRoom setInset: (ScriptID 20 0))
					(-1 0)
					(else  (messager say: 4 6 5))
				)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance pianoplayer of Prop
	(properties
		x 116
		y 83
		noun 5
		view 315
		loop 1
		cel 13
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(6
				(switch (curRoom setInset: (ScriptID 20 0))
					(-1 0)
					(else  (messager say: 5 6 5))
				)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance sleeper of View
	(properties
		x 287
		y 117
		noun 6
		view 317
		loop 1
		cel 3
	)
)

(instance woman1 of View
	(properties
		x 261
		y 100
		noun 11
		view 317
		cel 3
		priority 8
		signal $0010
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(1
				(if local0
					(messager say: 11 1 1)
					(= local0 0)
				else
					(messager say: 11 1 2)
				)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance woman2 of View
	(properties
		x 236
		y 101
		noun 11
		view 317
		loop 2
		priority 8
		signal $4010
	)
	
	(method (doVerb theVerb)
		(woman1 doVerb: theVerb)
	)
)

(instance dancersA of Prop
	(properties
		x 183
		y 111
		noun 8
		view 313
		cel 1
		signal $1000
	)
)

(instance dancersB of Prop
	(properties
		x 151
		y 121
		noun 8
		view 313
		loop 3
		cel 5
		signal $1000
	)
)

(instance dancersC of Prop
	(properties
		x 106
		y 114
		noun 8
		view 313
		loop 4
		cel 2
	)
)

(instance bathroomDoor of LbDoor
	(properties
		x 215
		y 51
		noun 9
		approachX 228
		approachY 109
		view 311
		entranceTo 320
		moveToX 255
		moveToY 95
		enterType 0
		exitType 0
	)
	
	(method (createPoly)
		(super createPoly: 211 100 253 100 253 109 211 109)
	)
)

(instance endOfBar of Feature
	(properties
		y 136
		nsTop 99
		nsLeft 90
		nsBottom 131
		nsRight 110
	)
)

(instance southExitFeature of ExitFeature
	(properties
		nsTop 184
		nsBottom 189
		nsRight 319
		cursor 11
		exitDir 3
		noun 12
	)
	
	(method (handleEvent event)
		(cond 
			((not (user input?)))
			((not (self onMe: mouseX (- mouseY 10))))
			(
				(or
					(and
						(== (event type?) evKEYBOARD)
						(!= (event message?) KEY_RETURN)
					)
					(and
						(== (event type?) evMOUSEBUTTON)
						(event modifiers?)
					)
					(not (OneOf (event type?) 1 4))
				)
				(= lastCursor -1)
			)
			((== theCursor ((theIconBar at: 1) cursor?)) (event claimed: 1) (messager say: noun 1))
			((!= theCursor cursor))
			(else (event claimed: 1) (curRoom setScript: sLeaveSouth))
		)
	)
)

(instance Bartender of Narrator
	(properties
		x 100
		y 100
		talkWidth 150
		back 15
	)
	
	(method (init)
		(= font userFont)
		(super init: &rest)
	)
)

(instance Singer of Narrator
	(properties
		x 100
		y 100
		talkWidth 150
		back 15
	)
	
	(method (init)
		(= font userFont)
		(super init: &rest)
	)
)

(instance PianoPlayer of Narrator
	(properties
		x 100
		y 100
		talkWidth 150
		back 15
	)
	
	(method (init)
		(= font userFont)
		(super init: &rest)
	)
)
