;;; Sierra Script 1.0 - (do not remove this comment)
(script# 320)
(include game.sh) (include "320.shm")
(use Main)
(use LBRoom)
(use ExitFeature)
(use Print)
(use Scaler)
(use PolyPath)
(use Polygon)
(use Feature)
(use MoveFwd)
(use LoadMany)
(use Motion)
(use Actor)
(use System)

(public
	rm320 0
)

(local
	local0
)
(instance rm320 of LBRoom
	(properties
		noun N_ROOM
		picture 320
		south 310
		vanishingY -60
	)
	
	(method (init)
		(LoadMany RES_VIEW 321 322 318 831 830)
		(Load RES_SOUND 321)
		(ego
			init:
			x: 133
			y: 182
			actions: aPutOnDress
			normalize: (if (ego wearingGown?) 831 else 830)
			setScale: Scaler 145 0 190 0
		)
		(switch prevRoomNum
			(south
				(if (not (Btst 24))
					(curRoom setScript: sEnterRm1stTime)
				else
					(curRoom setScript: sEnterRmNthTime)
				)
			)
			(else 
				(ego posn: 160 130)
				(theGame handsOn:)
			)
		)
		(super init:)
		(self
			addObstacle:
				((Polygon new:)
					type: 3
					init:
						236
						150
						199
						102
						146
						102
						120
						102
						107
						109
						144
						109
						128
						134
						110
						146
						77
						146
						78
						189
						182
						189
						181
						150
					yourself:
				)
		)
		(theMusic2 number: 321 loop: -1 flags: 1 play:)
		(sleazy
			approachVerbs: 4 2 6
			init:
			setScript: sSheAnimates
		)
		(smoke init: setScript: sDoSomethingLaura)
		(partition init:)
		(stalls init:)
		(sink init:)
		(rm320Window init:)
		(couch setOnMeCheck: 1 16384 init:)
		(southExitFeature init:)
	)
	
	(method (doit)
		(cond 
			(script)
			((IsObjectOnControl ego 2) (curRoom setScript: sExitSouth))
		)
		(super doit:)
	)
)

(instance sExitSouth of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(= cycles 1)
			)
			(1
				(ego setHeading: 180 setMotion: MoveTo (ego x?) 250 self)
			)
			(2
				(theMusic fade: 127 30 12 0)
				(curRoom newRoom: 310)
				(self dispose:)
			)
		)
	)
)

(instance sEnterRm1stTime of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(= cycles 1)
			)
			(1
				(ego
					posn: 137 151
					setHeading: 1
					setMotion: MoveFwd 10 self
				)
			)
			(2 (messager say: 1 0 1 1 self))
			(3
				(Print
					addText: 12 0 0 0
					addButton: 1 13 0 0 1 5 (WhichLanguage 20 20 25 20 25)
					addButton: 1 13 0 0 2 5 (WhichLanguage 55 55 55 55 50)
					init:
				)
				(messager say: 1 0 1 2 self)
			)
			(4
				(Bset 24)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sEnterRmNthTime of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(= cycles 1)
			)
			(1
				(ego
					posn: 137 151
					setHeading: 1
					setMotion: MoveFwd 10 self
				)
			)
			(2
				(if (Btst 78)
					(messager say: 15 0 0 0 self)
					(Bclr 78)
				else
					(messager say: 1 0 3 0 self)
				)
			)
			(3
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sLauraChanges of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(= cycles 3)
			)
			(1
				(ego
					put: 32
					wearingGown: 1
					setMotion: PolyPath 208 150 self
				)
				((ScriptID 22 0) doit: 16)
				(theGame points: 1 132)
			)
			(2
				(ego setMotion: MoveTo 210 160 self)
			)
			(3
				(ego
					view: 321
					loop: 0
					setCel: 0
					posn: (- (ego x?) 3) (- (ego y?) 45)
					cycleSpeed: 10
					setScale: Scaler 100 100 190 0
					moveSpeed: 10
					setCycle: EndLoop self
				)
				(clothes init: cycleSpeed: 10 setCel: 0 setCycle: EndLoop)
			)
			(4
				(messager say: 2 0 0 0)
				(ego view: 321 loop: 1 setCycle: EndLoop self)
			)
			(5
				(ego
					view: 831
					setScale: Scaler 145 0 190 0
					loop: 2
					posn: (+ (ego x?) 3) (+ (ego y?) 45)
				)
				(= cycles 1)
			)
			(6
				(ego setCycle: Walk setMotion: MoveTo 208 150 self)
			)
			(7
				(= local0 1)
				(clothes addToPic:)
				(theGame handsOn:)
				(Bset 78)
				((ScriptID 21 1) doit: 801)
				(ego normalize: 831 setScale: Scaler 145 0 190 0)
				(self dispose:)
			)
		)
	)
)

(instance sSheAnimates of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(switch (Random 0 2)
					(0
						(self setScript: sSheMoves self)
					)
					(else 
						(self setScript: sSheSmokes self)
					)
				)
			)
			(1 (= cycles 70))
			(2 (= state -1) (= cycles 1))
		)
	)
)

(instance sSheSmokes of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(sleazy setLoop: 0 setCycle: CycleTo 9 1 self)
			)
			(1 (smoke setCycle: EndLoop self))
			(2
				(sleazy setCycle: EndLoop self)
				(smoke cel: 0)
			)
			(3 (= cycles (Random 30 70)))
			(4 (self dispose:))
		)
	)
)

(instance sSheMoves of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(sleazy cel: 0 setLoop: 2 setCycle: EndLoop self)
			)
			(1 (= cycles 100))
			(2
				(switch (Random 0 2)
					(0
						(messager say: 10 0 0 1 self)
					)
					(1
						(messager say: 10 0 0 2 self)
					)
					(2
						(messager say: 10 0 0 3 self)
					)
				)
			)
			(3
				(sleazy setLoop: 3 setCycle: Forward)
				(= cycles (Random 30 60))
			)
			(4 (sleazy setCycle: EndLoop self))
			(5
				(sleazy setLoop: 2)
				(= cycles 1)
			)
			(6
				(sleazy cel: (sleazy lastCel:) setCycle: BegLoop self)
			)
			(7 (self dispose:))
		)
	)
)

(instance sDoSomethingLaura of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= seconds 50))
			(1
				(if (ego wearingGown?)
					(self dispose:)
				else
					(messager say: 11 0 0 1 self)
				)
			)
			(2 (= state -1) (= cycles 1))
		)
	)
)

(instance aPutOnDress of Actions
	(properties)
	
	(method (doVerb theVerb &tmp temp0)
		(switch theVerb
			(42
				(curRoom setScript: sLauraChanges)
			)
			(else  0)
		)
	)
)

(instance sleazy of Prop
	(properties
		x 109
		y 113
		noun 3
		approachX 145
		approachY 124
		view 322
		priority 10
		signal $0010
		cycleSpeed 10
	)
	
	(method (doVerb theVerb &tmp temp0)
		(switch theVerb
			(2
				((ScriptID 21 0) doit: 269)
				(messager say: 3 2)
			)
			(6
				(switch (curRoom setInset: (ScriptID 20 0))
					(269 (messager say: 3 6 7))
					(264 (messager say: 3 6 4))
					(520 (messager say: 3 6 9))
					(-1 0)
					(else  (messager say: 3 6 5))
				)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance smoke of Prop
	(properties
		x 113
		y 96
		view 318
		loop 6
	)
	
	(method (init)
		(self posn: (+ (sleazy x?) 4) (- (sleazy y?) 17))
		(super init:)
	)
)

(instance clothes of Prop
	(properties
		x 207
		y 114
		view 321
		loop 2
		cel 10
		priority 15
		signal $4010
	)
)

(instance partition of Feature
	(properties
		x 217
		y 130
		noun 4
		nsTop 109
		nsLeft 185
		nsBottom 151
		nsRight 250
		sightAngle 40
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(1
				(if local0
					(messager say: 4 1 8)
				else
					(messager say: 4 1 6)
				)
			)
			(4
				(if local0
					(messager say: 4 4 8)
				else
					(messager say: 4 4 6)
				)
			)
			(42
				(curRoom setScript: sLauraChanges)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance stalls of Feature
	(properties
		x 170
		y 68
		noun 5
		nsTop 40
		nsLeft 139
		nsBottom 96
		nsRight 202
		sightAngle 40
	)
)

(instance sink of Feature
	(properties
		x 221
		y 91
		noun 6
		nsTop 78
		nsLeft 203
		nsBottom 104
		nsRight 239
		sightAngle 40
	)
)

(instance rm320Window of Feature
	(properties
		x 239
		y 57
		noun 9
		nsTop 51
		nsLeft 229
		nsBottom 63
		nsRight 249
		sightAngle 40
	)
)

(instance couch of Feature
	(properties
		y 101
		noun 8
	)
)

(instance southExitFeature of ExitFeature
	(properties
		nsTop 149
		nsLeft 69
		nsBottom 154
		nsRight 192
		cursor 11
		exitDir 3
		noun 14
	)
)
