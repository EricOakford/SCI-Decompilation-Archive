;;; Sierra Script 1.0 - (do not remove this comment)
(script# 406)
(include game.sh)
(use Main)
(use MoveToCoords)
(use Inertia)
(use SQRoom)
(use Sq4Feature)
(use LoadMany)
(use Sound)
(use Motion)
(use System)

(public
	rm406 0
)

(local
	local0
	local1
	local2
	underBits
	local4 =  999
	local5
	local6
	local7
	local8
	blastX
	blastY
)
(instance rm406 of SQRoom
	(properties
		picture 406
		east 411
		south 405
	)
	
	(method (init)
		(HandsOff)
		(if (= local1 (Btst fPoliceAtCeiling)) (LoadMany VIEW 408 409 28))
		(Load SOUND 105)
		(if (= local0 (Btst fPoliceAtArcade)) (blast init:))
		(self setRegions: MALL)
		(switch prevRoomNum
			(south
				(self setScript: enterScript 0 south style: 10)
			)
			(else 
				(self setScript: enterScript 0 east style: 11)
			)
		)
		(if (and local1 (== prevRoomNum east))
			((ScriptID MALL 6) init: hide: setScript: egoFollowed)
			((ScriptID MALL 7) init: hide:)
		)
		(ego setPri: 6 init:)
		(super init:)
		((ScriptID MALL 6) show:)
		((ScriptID MALL 7) show:)
		(features add: theMall dome eachElementDo: #init doit:)
	)
	
	(method (doit)
		(cond 
			((or script local2 (== (ego view?) 409)) (ego edgeHit: 0))
			((OneOf (ego edgeHit?) NORTH WEST)
				(HandsOff)
				(self setScript: stayInScript 0 (ego edgeHit?))
			)
			((OneOf (ego edgeHit?) SOUTH EAST) (HandsOff) (self setScript: exitScript 0 (ego edgeHit?)))
			(
				(and
					local0
					(< (ego x?) 155)
					(< 12 (ego y?))
					(< (ego y?) 140)
					(not (== (curRoom script?) stayInScript))
				)
				(if (not ((ScriptID MALL 6) script?))
					((ScriptID MALL 6) init: setScript: (swimAndShoot new:))
				)
				(if (not ((ScriptID MALL 7) script?))
					((ScriptID MALL 7) init: setScript: (swimAndShoot new:))
				)
			)
		)
		(super doit: &rest)
	)
)

(instance enterScript of Script
	(properties)
	
	(method (changeState newState &tmp temp0)
		(switch (= state newState)
			(0
				(switch register
					((curRoom east?)
						(= temp0 (CelWide (ego view?) (ego loop?) (ego cel?)))
						((ScriptID MALL 4)
							init: ego
							inertizing: 1
							inertia: 5
							oldDir: 270
							xOff: -1
						)
						(ego
							setHeading: 270
							setCycle: Swim
							x: (- 319 (+ (/ temp0 2) 1))
							y: (if (< (ego y?) 20) 20 else (ego y?))
						)
						(= cycles 1)
					)
					((curRoom south?)
						((ScriptID MALL 4) init: ego)
						(= temp0 (CelHigh (ego view?) (ego loop?) (ego cel?)))
						(ego
							setCycle: Swim
							x: (ego x?)
							y: (+ 189 temp0)
							setMotion: MoveToY (- 189 temp0) self
						)
					)
				)
			)
			(1
				(if (not ((ScriptID MALL 6) script?)) (HandsOn))
				(self dispose:)
			)
		)
	)
)

(instance exitScript of Script
	(properties)
	
	(method (changeState newState &tmp temp0 egoX egoY)
		(switch (= state newState)
			(0
				((ScriptID MALL 4) dispose:)
				(if (== register 3) (= temp0 180) else (= temp0 90))
				(ego setHeading: temp0 self)
			)
			(1
				(if (== register 3)
					(= egoX (ego x?))
					(= egoY 220)
				else
					(= egoX 350)
					(= egoY (ego y?))
				)
				(ego setMotion: MoveTo egoX egoY self)
			)
			(2
				(if (== register 3) (= temp0 405) else (= temp0 411))
				(ego setPri: -1)
				(curRoom newRoom: temp0)
			)
		)
	)
)

(instance stayInScript of Script
	(properties)
	
	(method (changeState newState &tmp egoX egoY)
		(switch (= state newState)
			(0
				((ego code?) xOff: 0 yOff: 0 inertia: 0)
				(if (== register 1)
					(= egoX (ego x?))
					(= egoY -30)
				else
					(= egoX -30)
					(= egoY (ego y?))
				)
				(ego setMotion: MoveTo egoX egoY self)
			)
			(1
				((ego code?) xOff: 0 yOff: 0 inertia: 0)
				(if (== register 1)
					(if (> (ego x?) 300) (ego x: 300))
					(if (< (ego x?) 20) (ego x: 20))
					(Animate (cast elements?) 0)
					(= egoX (ego x?))
					(= egoY 30)
				else
					(if (> (ego y?) 180) (ego y: 180))
					(if (< (ego y?) 20) (ego y: 20))
					(Animate (cast elements?) 0)
					(= egoX 30)
					(= egoY (ego y?))
				)
				(ego setMotion: MoveTo egoX egoY self)
			)
			(2 (HandsOn) (self dispose:))
		)
	)
)

(instance swimAndShoot of Script
	(properties)
	
	(method (doit)
		(if
			(and
				(not (curRoom script?))
				(< 0 state)
				(< state 3)
				(< (- (client x?) (ego x?)) 82)
			)
			(client setMotion: 0 setCycle: 0)
			(= local2 1)
			(self changeState: 3)
		)
		(super doit: &rest)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(client x: 1000)
				(= seconds (Random 1 5))
			)
			(1
				(if (not local1) (= local1 1) (Bset fPoliceAtCeiling))
				(client
					view: 408
					setLoop: 1
					cel: 0
					setCycle: EndLoop
					setStep: 2 2
					x: 355
				)
				(if (== client (ScriptID MALL 6))
					(client
						y: (- (ego y?) (Random 30 50))
						setMotion: MoveTo 278 (- (ego y?) 23) self
					)
				else
					(client
						y: (+ (ego y?) (Random 30 50))
						setMotion: MoveTo 288 (+ (ego y?) 15) self
					)
				)
			)
			(2 (= cycles 7))
			(3
				(if underBits
					(self changeState: (- state 1))
				else
					(= underBits 1)
					(client view: 409 setLoop: 1 cel: 0 setCycle: CycleTo 1 1 self)
					(= local2
						(if
							(or
								(not (ego mover?))
								(== (ego y?) ((ego mover?) y?))
							)
							(not
								(if (> (ego y?) 180)
									((ScriptID MALL 4) inertia?)
								else
									0
								)
							)
						else
							0
						)
					)
				)
			)
			(4
				(= local8 (Max 2 (- (client y?) 4)))
				(= local7 (Min 318 (- (client x?) 46)))
				(= blastY (Min 188 (Max 2 (ego y?))))
				(= blastX
					(if local2 (Max (ego x?) 2) else (Random 2 8))
				)
				(if (< local8 blastY)
					(= local5 local8)
					(= local6 blastY)
				else
					(= local5 blastY)
					(= local6 local8)
				)
				(= underBits
					(Graph
						GSaveBits
						(- local5 1)
						(- blastX 1)
						(+ local6 1)
						(+ local7 1)
						1
					)
				)
				(Graph
					GDrawLine
					local8
					local7
					blastY
					blastX
					(VGAOrEGA myTextColor3 myTextColor13)
					1
					-1
				)
				(Graph
					GReAnimate
					(- local5 1)
					(- blastX 1)
					(+ local6 1)
					(+ local7 1)
					1
				)
				(aSound number: 105 loop: 1 vol: 127 play:)
				(= cycles 1)
			)
			(5
				(Graph GRestoreBits underBits)
				(Graph
					GReAnimate
					(- local5 1)
					(- blastX 1)
					(+ local6 1)
					(+ local7 1)
					1
				)
				(= underBits 0)
				(blast cel: 0 posn: blastX blastY setCycle: EndLoop)
				(client
					setLoop: 3
					cel: 0
					setCycle: Forward
					setStep: 2 1
					cycleSpeed: 6
					setMotion: MoveTo 360 (- (client y?) 6) self
				)
				(if local2
					(HandsOff)
					((ScriptID MALL 4) inertia: 0)
					(ego
						view: 409
						setLoop: 5
						cel: 0
						setMotion: 0
						setCycle: Forward
					)
				)
			)
			(6
				(if local2 (EgoDead iconLASER deathSKATELUMP) else (client dispose:))
			)
		)
	)
)

(instance egoFollowed of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				((ScriptID MALL 7)
					view: 408
					setLoop: 0
					posn: 40 168
					setStep: 1 1
					setMotion: MoveTo 130 130
					cycleSpeed: 12
					setCycle: Forward
				)
				((ScriptID MALL 6)
					view: 408
					setLoop: 0
					cel: 0
					setCycle: Forward
					setStep: 2 2
					posn: 110 (- (ego y?) 6)
					setMotion: MoveTo 138 (ego y?) self
				)
			)
			(1
				((ScriptID MALL 7) cycleSpeed: 18 moveSpeed: 1)
				((ScriptID MALL 6)
					view: 409
					setLoop: 0
					cel: 0
					setCycle: CycleTo 1 1 self
				)
				(HandsOff)
			)
			(2
				(= local5 (Max 2 (- ((ScriptID MALL 6) y?) 4)))
				(= local7 (+ ((ScriptID MALL 6) x?) 46))
				(= local6 (Min 188 (ego y?)))
				(= blastX (Min 318 (ego x?)))
				(= underBits
					(Graph
						GSaveBits
						(- local5 1)
						(- local7 1)
						(+ local6 1)
						(+ blastX 1)
						1
					)
				)
				(Graph
					GDrawLine
					local5
					local7
					local6
					blastX
					(VGAOrEGA myTextColor3 myTextColor13)
					1
					-1
				)
				(Graph
					GReAnimate
					(- local5 1)
					(- local7 1)
					(+ local6 1)
					(+ blastX 1)
					1
				)
				(aSound number: 105 loop: 1 vol: 127 play:)
				(= cycles 1)
			)
			(3
				(Graph GRestoreBits underBits)
				(Graph
					GReAnimate
					(- local5 1)
					(- local7 1)
					(+ local6 1)
					(+ blastX 1)
					1
				)
				(blast cel: 0 posn: blastX local6 setCycle: EndLoop)
				((ScriptID MALL 6)
					setLoop: 3
					cel: 0
					setCycle: Forward
					setStep: 2 1
					cycleSpeed: 6
					setStep: 4 3
					setMotion: MoveTo 0 (- ((ScriptID MALL 6) y?) 6)
				)
				(ego view: 409 setLoop: 4 cel: 0 setCycle: Forward)
				(= cycles 22)
			)
			(4 (EgoDead iconLASER deathSKATEORAMA))
		)
	)
)

(instance blast of Sq4Prop
	(properties
		view 28
	)
)

(instance dome of Sq4Feature
	(properties
		x 190
		sightAngle 180
		lookStr 1
	)
	
	(method (onMe)
		(return 1)
	)
)

(instance theMall of Sq4Feature
	(properties
		x 110
		y 80
		sightAngle 180
	)
	
	(method (onMe param1)
		(return (& (OnControl 4 (param1 x?) (param1 y?)) $0002))
	)
)

(instance aSound of Sound
	(properties)
)
