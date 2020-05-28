;;; Sierra Script 1.0 - (do not remove this comment)
(script# 411)
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
	rm411 0
)

(local
	local0
	local1
	standingStill
	underbits
	local4
	local5
	local6
	local7
	local8
	local9
)
(instance rm411 of SQRoom
	(properties
		picture 411
		south 410
		west 406
	)
	
	(method (init)
		(HandsOff)
		(if (= local1 (Btst fPoliceAtCeiling))
			(LoadMany VIEW 408 409 28)
			(Load SOUND 105)
		)
		(if (= local0 (Btst fPoliceAtArcade)) (blast init:))
		(self setRegions: MALL)
		(switch prevRoomNum
			(south
				(self setScript: enterScript 0 south style: 10)
			)
			(else 
				(self setScript: enterScript 0 west style: 12)
			)
		)
		(if (and local1 (== prevRoomNum west))
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
			((or script standingStill (== (ego view?) 409)) (ego edgeHit: 0))
			((OneOf (ego edgeHit?) 1 2)
				(HandsOff)
				(self setScript: stayInScript 0 (ego edgeHit?))
			)
			((OneOf (ego edgeHit?) 3 4) (HandsOff) (self setScript: exitScript 0 (ego edgeHit?)))
			(
				(and
					local0
					(> (ego x?) 165)
					(< 12 (ego y?))
					(< (ego y?) 140)
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
					((curRoom west?)
						(= temp0 (CelWide (ego view?) (ego loop?) (ego cel?)))
						((ScriptID MALL 4)
							init: ego
							inertizing: 1
							inertia: 5
							oldDir: 90
							xOff: 1
						)
						(ego
							setHeading: 90
							setCycle: Swim
							x: (+ (/ temp0 2) 1)
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
				(if (== register 3) (= temp0 180) else (= temp0 270))
				(ego setHeading: temp0 self)
			)
			(1
				(if (== register 3)
					(= egoX (ego x?))
					(= egoY 220)
				else
					(= egoX -30)
					(= egoY (ego y?))
				)
				(ego setMotion: MoveTo egoX egoY self)
			)
			(2
				(if (== register 3) (= temp0 410) else (= temp0 406))
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
					(= egoX 350)
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
					(= egoX 290)
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
				(< (- (ego x?) (client x?)) 82)
			)
			(client setMotion: 0 setCycle: 0)
			(= standingStill 1)
			(self changeState: 3)
		)
		(super doit: &rest)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(client y: -1000)
				(= seconds (Random 1 4))
			)
			(1
				(if (not local1) (= local1 1) (Bset fPoliceAtCeiling))
				(client
					view: 408
					setLoop: 0
					cel: 0
					setCycle: EndLoop
					setStep: 2 2
					x: -34
				)
				(if (== client (ScriptID MALL 6))
					(client
						y: (- (ego y?) (Random 30 50))
						setMotion: MoveTo 42 (- (ego y?) 23) self
					)
				else
					(client
						y: (+ (ego y?) (Random 30 50))
						setMotion: MoveTo 32 (+ (ego y?) 15) self
					)
				)
			)
			(2 (= cycles 7))
			(3
				(if underbits
					(self changeState: (- state 1))
				else
					(= underbits 1)
					(client view: 409 setLoop: 0 cel: 0 setCycle: CycleTo 1 1 self)
					(= standingStill
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
				(= local7 (Max 1 (- (client y?) 4)))
				(= local6 (+ (client x?) 46))
				(= local9 (Min 188 (Max 2 (ego y?))))
				(= local8
					(if standingStill (Min (ego x?) 318) else (Random 312 318))
				)
				(if (< local7 local9)
					(= local4 local7)
					(= local5 local9)
				else
					(= local4 local9)
					(= local5 local7)
				)
				(= underbits
					(Graph
						GSaveBits
						(- local4 1)
						(- local6 1)
						(+ local5 1)
						(+ local8 1)
						1
					)
				)
				(Graph
					GDrawLine
					local7
					local6
					local9
					local8
					(VGAOrEGA myTextColor3 myTextColor13)
					1
					-1
				)
				(Graph
					GReAnimate
					(- local4 1)
					(- local6 1)
					(+ local5 1)
					(+ local8 1)
					1
				)
				(aSound number: 105 loop: 1 vol: 127 play:)
				(= cycles 1)
			)
			(5
				(Graph GRestoreBits underbits)
				(Graph
					GReAnimate
					(- local4 1)
					(- local6 1)
					(+ local5 1)
					(+ local8 1)
					1
				)
				(= underbits 0)
				(blast cel: 0 posn: local8 local9 setCycle: EndLoop)
				(client
					setLoop: 2
					cel: 0
					setCycle: Forward
					setStep: 2 1
					cycleSpeed: 0
					setMotion: MoveTo -36 (- (client y?) 6) self
				)
				(if standingStill
					(HandsOff)
					((ScriptID MALL 4) inertia: 0)
					(ego
						view: 409
						setLoop: 4
						cel: 0
						setMotion: 0
						setCycle: Forward
					)
				)
			)
			(6
				(if standingStill (EgoDead iconLASER deathSKATELUMP) else (client dispose:))
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
					setLoop: 1
					posn: 280 168
					setStep: 1 1
					setMotion: MoveTo 190 130
					cycleSpeed: 1
					setCycle: Forward
				)
				((ScriptID MALL 6)
					view: 408
					setLoop: 1
					cel: 0
					setCycle: Forward
					setStep: 2 2
					posn: 210 (- (ego y?) 6)
					setMotion: MoveTo 182 (ego y?) self
				)
			)
			(1
				((ScriptID MALL 7) cycleSpeed: 2 moveSpeed: 1)
				((ScriptID MALL 6)
					view: 409
					setLoop: 1
					cel: 0
					setCycle: CycleTo 1 1 self
				)
				(HandsOff)
			)
			(2
				(= local4 (- ((ScriptID MALL 6) y?) 4))
				(= local6 (- ((ScriptID MALL 6) x?) 46))
				(= local5 (Min 188 (ego y?)))
				(= local8 (Max 2 (ego x?)))
				(= underbits
					(Graph
						GSaveBits
						(- local4 1)
						(- local8 1)
						(+ local5 1)
						(+ local6 1)
						1
					)
				)
				(Graph
					GDrawLine
					local4
					local6
					local5
					local8
					(VGAOrEGA myTextColor3 myTextColor13)
					1
					-1
				)
				(Graph
					GReAnimate
					(- local4 1)
					(- local8 1)
					(+ local5 1)
					(+ local6 1)
					1
				)
				(aSound number: 105 loop: 1 vol: 127 play:)
				(= cycles 1)
			)
			(3
				(Graph GRestoreBits underbits)
				(Graph
					GReAnimate
					(- local4 1)
					(- local8 1)
					(+ local5 1)
					(+ local6 1)
					1
				)
				(blast cel: 0 posn: local8 local5 setCycle: EndLoop)
				((ScriptID MALL 6)
					setLoop: 2
					cel: 0
					setCycle: Forward
					setStep: 2 1
					cycleSpeed: 0
					setStep: 4 3
					setMotion: MoveTo 320 (- ((ScriptID MALL 6) y?) 6)
				)
				(ego view: 409 setLoop: 5 cel: 0 setCycle: Forward)
				(= cycles 22)
			)
			(4 (EgoDead 8 15))
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
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(1
				(narrator modNum: 406 say: 1)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
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
	
	(method (doVerb theVerb)
		(switch theVerb
			(1
				(narrator modNum: 405 say: 7)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
	
	(method (onMe param1)
		(return (& (OnControl 4 (param1 x?) (param1 y?)) $0002))
	)
)

(instance aSound of Sound
	(properties)
)
