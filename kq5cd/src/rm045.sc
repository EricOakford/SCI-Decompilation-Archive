;;; Sierra Script 1.0 - (do not remove this comment)
(script# 45)
(include sci.sh)
(use Main)
(use Intrface)
(use Waters)
(use CodeCue)
(use KQ5Room)
(use RandCyc)
(use PolyPath)
(use Polygon)
(use LoadMany)
(use RFeature)
(use Motion)
(use Actor)
(use System)

(public
	rm045 0
)

(local
	[local0 2]
	[theX 22] = [144 2 80 93 2 80 94 52 80 400 0 80 400 0 80 400 0 80 400 0 80 1]
	[local24 40] = [0 189 0 0 319 0 319 92 293 90 275 78 207 81 159 76 128 85 136 91 114 95 126 99 76 109 106 110 103 117 63 120 78 124 24 146 40 166 22 189]
	[local64 8] = [147 110 176 114 154 120 120 114]
	[local72 8] = [154 129 173 125 189 129 171 131]
	[local80 8] = [254 88 294 94 260 98 243 94]
	[local88 9] = [1000 130 50 4 11 24 19 23 30]
)
(instance rm045 of KQ5Room
	(properties
		picture 45
		east 47
		south 44
	)
	
	(method (init)
		(super init:)
		(= cedricX 147)
		(= cedricY 99)
		(= global325 3068)
		(self
			setRegions: 220
			setFeatures: beach rocks rocks2 waterFall
		)
		(if
			(and
				(not (Btst 54))
				(not (curRoom script?))
				(not (Btst 105))
			)
			(self setRegions: 202)
		)
		(if (not (Btst 89))
			(theGame setScript: boatHereGraham)
		)
		(LoadMany 128 26 28 30)
		(theAudio doNotStop: 1 number: 7054 loop: -1 play:)
		(if (== (theGame detailLevel:) 3) (water init:))
		(surf3 setScript: waves)
		(switch prevRoomNum
			(east 0)
			(south
				(ego posn: (ego x?) 186 view: 0 init:)
				(if
				(and (cast contains: globalCedric) (not (Btst 105)))
					(self setScript: (ScriptID 202 1))
				)
			)
			(else  (ego posn: 84 186 init:))
		)
		(if (cast contains: globalCedric)
			(globalCedric setPri: 8)
		)
		(poly1 points: @local24 size: 20)
		(poly2 points: @local64 size: 4)
		(poly3 points: @local72 size: 4)
		(poly4 points: @local80 size: 4)
		(self addObstacle: poly1 poly2 poly3 poly4)
	)
	
	(method (doit &tmp temp0 temp1 temp2)
		(= temp1 0)
		(cond 
			(script (script doit:))
			(
			(and (== (ego view?) 69) (ego cycler?) (ego mover?)))
			(
				(and
					(& (= temp2 (ego onControl: 1)) $2000)
					(!= (ego view?) 26)
				)
				(ego view: 26)
			)
			((and (& temp2 $0610) (!= (ego view?) 30)) (curRoom setScript: flail))
			((and (!= (ego view?) 0) (& temp2 $0002)) (ego setStep: 3 2 view: 0))
			(
				(and
					(ego edgeHit?)
					(= temp0 (self edgeToRoom: (ego edgeHit?)))
				)
				(if
				(and (cast contains: globalCedric) (!= temp0 47))
					(ego setLoop: -1)
					((ScriptID 202 2) register: (ego edgeHit?))
					(self setScript: (ScriptID 202 2))
				else
					(Bset 107)
					(ego setLoop: -1)
					(curRoom newRoom: temp0)
				)
			)
		)
	)
	
	(method (dispose)
		(theMusic2 fade:)
		(DisposeScript 401)
		(DisposeScript 941)
		(super dispose:)
	)
	
	(method (handleEvent event &tmp [temp0 100])
		(cond 
			((event claimed?) (return))
			(script (return))
		)
	)
	
	(method (newRoom n)
		(super newRoom: n)
		(if (not (OneOf n 44 45 46)) (theMusic fade:))
	)
)

(instance flail of Script
	(properties)
	
	(method (changeState newState &tmp temp0)
		(switch (= state newState)
			(0
				(HandsOff)
				((ego head?) hide:)
				(ego
					view: 69
					setLoop: 4
					illegalBits: 0
					setCycle: Forward
					setStep: 4 2
					setMotion: PolyPath 340 100 self
				)
			)
			(1 (client setScript: 0))
		)
	)
)

(instance waves of Script
	(properties)
	
	(method (changeState newState &tmp temp0)
		(switch (= state newState)
			(0
				(surf1
					init:
					show:
					setCycle: EndLoop self
					setPri: 1
					ignoreActors:
					cycleSpeed: 10
				)
				(surf2
					init:
					setCycle: RandCycle
					setPri: 1
					ignoreActors:
					cycleSpeed: 10
				)
				(surf3
					init:
					setCycle: Forward
					setPri: 1
					ignoreActors:
					cycleSpeed: 3
				)
				(surf4
					init:
					setCycle: Forward
					setPri: 1
					ignoreActors:
					cycleSpeed: 6
				)
			)
			(1
				(surf1 setCycle: BegLoop self)
				(surf2 setCycle: BegLoop)
			)
			(2 (= state -1) (= seconds 1))
		)
	)
)

(instance cedricScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(globalCedric
					view: 618
					setLoop: 11
					setCycle: Forward
					cycleSpeed: 0
					moveSpeed: 0
					setStep: 5 5
					setMotion: MoveTo (Random 1 100) 50 self
				)
			)
			(1
				(globalCedric
					setLoop: 12
					setMotion: MoveTo (Random 200 320) 50 self
				)
			)
			(2 (= state -1) (= cycles 1))
		)
	)
)

(instance water of Waters
	(properties
		x 144
		y 2
		view 617
		signal $6030
		detailLevel 3
	)
	
	(method (getLoop)
		(= x [theX pos])
		(= y [theX (++ pos)])
		(= cel [theX (++ pos)])
	)
	
	(method (saveLoop)
		(= [theX pos] cel)
		(++ pos)
	)
)

(instance surf1 of Prop
	(properties
		x 276
		y 147
		view 619
		cycleSpeed 2
		detailLevel 3
	)
)

(instance surf2 of Prop
	(properties
		x 278
		y 187
		view 619
		loop 1
		cycleSpeed 2
		detailLevel 3
	)
)

(instance surf3 of Prop
	(properties
		x 191
		y 103
		view 617
		loop 3
		cycleSpeed 2
		detailLevel 3
	)
)

(instance surf4 of Prop
	(properties
		x 285
		y 124
		view 617
		loop 4
		cycleSpeed 2
		detailLevel 3
	)
)

(instance rocks2 of RFeature
	(properties
		nsTop 24
		nsLeft 181
		nsBottom 85
		nsRight 319
	)
	
	(method (handleEvent event)
		(if
			(or
				(event claimed?)
				(not (== (event type?) 16384))
				(not (MousedOn self event))
			)
			(return)
		else
			(switch (event message?)
				(JOY_UPRIGHT
					(SpeakAudio 511)
					(event claimed: 1)
				)
			)
		)
	)
)

(instance beach of RFeature
	(properties)
	
	(method (handleEvent event)
		(if
			(or
				(event claimed?)
				(not (== (event type?) 16384))
				(and
					(not (& (OnControl 4 (event x?) (event y?)) $2000))
					(not (& (OnControl 4 (event x?) (event y?)) $0040))
					(not (& (OnControl 4 (event x?) (event y?)) $0002))
					(not (& (OnControl 4 (event x?) (event y?)) $0400))
					(not (& (OnControl 4 (event x?) (event y?)) $0200))
				)
			)
			(return)
		else
			(switch (event message?)
				(JOY_UPRIGHT
					(SpeakAudio 512)
					(event claimed: 1)
				)
			)
		)
	)
)

(instance waterFall of RFeature
	(properties
		nsLeft 102
		nsBottom 68
		nsRight 179
	)
	
	(method (handleEvent event)
		(if
			(or
				(event claimed?)
				(not (== (event type?) 16384))
				(not (MousedOn self event))
			)
			(return)
		else
			(switch (event message?)
				(JOY_UPRIGHT
					(SpeakAudio 513)
					(event claimed: 1)
				)
				(JOY_RIGHT
					(SpeakAudio 514)
					(event claimed: 1)
				)
			)
		)
	)
)

(instance rocks of RFeature
	(properties
		nsBottom 113
		nsRight 97
	)
	
	(method (handleEvent event)
		(if
			(or
				(event claimed?)
				(not (== (event type?) 16384))
				(not (MousedOn self event))
			)
			(return)
		else
			(switch (event message?)
				(JOY_UPRIGHT
					(SpeakAudio 511)
					(event claimed: 1)
				)
			)
		)
	)
)

(instance boatHereGraham of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= seconds 3))
			(1
				(Bset 89)
				(proc762_1 @local88 3069 self)
			)
			(2 (self dispose:))
		)
	)
)

(instance poly1 of Polygon
	(properties
		type $0002
	)
)

(instance poly2 of Polygon
	(properties
		type $0002
	)
)

(instance poly3 of Polygon
	(properties
		type $0002
	)
)

(instance poly4 of Polygon
	(properties
		type $0002
	)
)
