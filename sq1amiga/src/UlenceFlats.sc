;;; Sierra Script 1.0 - (do not remove this comment)
(script# 702)
(include sci.sh)
(use Main)
(use Intrface)
(use PAvoid)
(use PChase)
(use Feature)
(use MoveFwd)
(use Reverse)
(use Grooper)
(use Sound)
(use Motion)
(use Game)
(use User)
(use Actor)
(use System)

(public
	ulenceRegion 0
	robot 1
)

(instance ulenceRegion of Rgn
	(properties)
	
	(method (init)
		(super init:)
		(if
			(and
				(!= (theMusic number?) 601)
				(OneOf curRoomNum 40 41 42 45 46 47)
				(OneOf prevRoomNum 40 41 42 43 45 46 47 48)
			)
			(theMusic number: 601 loop: -1 play:)
		)
		(if (Btst 33)
			(robot
				init:
				illegalBits: (if (OneOf curRoomNum 46 45) 64 else 0)
				posn:
					(switch (ego edgeHit?)
						(4 (+ droidX 319))
						(2 (- droidX 319))
						(else  (ego x?))
					)
					(switch (ego edgeHit?)
						(3 (- (curRoom horizon?) 10))
						(1 280)
						(else  droidY)
					)
				setLoop: roboGroop
				setCycle: Walk
				setMotion: PFollow ego 55
			)
			(ego setAvoider: PAvoider)
		)
		(mountains init:)
		(keronaSky init:)
		(bigMoon init:)
		(smallMoon init:)
		(radarPost init:)
		(desert init:)
	)
	
	(method (doit)
		(cond 
			((curRoom script?) 0)
			((== (ego onControl: 1) 16384) (curRoom setScript: zapEgo))
		)
		(super doit:)
	)
	
	(method (dispose)
		(= droidX (robot x?))
		(= droidY (robot y?))
		(= keep (OneOf newRoomNum 40 41 42 44 45 46 47))
		(super dispose: &rest)
	)
	
	(method (newRoom n)
		(if
		(and (!= n 45) (not (OneOf n 40 41 42 45 46 47)))
			(theMusic fade:)
		)
		(super newRoom: n)
	)
)

(instance mountains of Feature
	(properties
		description {mountains}
		onMeCheck $0080
		lookStr {In the distance is the galaxy-famous Skihairy mountain range. Nestled in its southernmost slopes is the renowned YoMammy National Park, usually hip-deep in tourists this time of year. Oh, well - maybe next trip.}
	)
	
	(method (doVerb theVerb)
		(= x ((User curEvent?) x?))
		(= y ((User curEvent?) y?))
		(switch theVerb
			(3 (Print 702 0))
			(5 (Print 702 1))
			(12 (Print 702 2))
			(11 (Print 702 3))
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance keronaSky of Feature
	(properties
		description {sky}
		onMeCheck $0200
		lookStr {What a sky this planet has! You've never seen its like outside a toxic waste dump.}
	)
	
	(method (doVerb theVerb)
		(= x ((User curEvent?) x?))
		(= y ((User curEvent?) y?))
		(switch theVerb
			(3 (Print 702 4))
			(5 (Print 702 5))
			(12 (Print 702 6))
			(11 (Print 702 7))
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance bigMoon of Feature
	(properties
		description {big moon}
		onMeCheck $0100
		lookStr {Kerona has two moons. This one's the bigger of two.}
	)
	
	(method (doVerb)
		(= x ((User curEvent?) x?))
		(= y ((User curEvent?) y?))
		(super doVerb: &rest)
	)
)

(instance smallMoon of Feature
	(properties
		description {small moon}
		onMeCheck $2000
		lookStr {Kerona has two moons. This one's the smaller of two.}
	)
	
	(method (doVerb)
		(= x ((User curEvent?) x?))
		(= y ((User curEvent?) y?))
		(super doVerb: &rest)
	)
)

(instance radarPost of Feature
	(properties
		description {radar post}
		onMeCheck $0400
		lookStr {The settlement of Ulence Flats is surrounded by these force field generators. They repel such undesirables such as the grell which thrive below the sands. It will allow only airborne vehicles in or out.}
	)
	
	(method (init)
		(= y (if (== curRoomNum 41) 200 else 1))
		(super init: &rest)
	)
	
	(method (doVerb theVerb)
		(= x ((User curEvent?) x?))
		(if (!= y 200) (= y ((User curEvent?) y?)))
		(switch theVerb
			(3 (Print 702 8))
			(4 (Print 702 9))
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance desert of Feature
	(properties
		description {desert}
		onMeCheck $0800
		lookStr {Kerona's relatively flat desert stretches out into the distance.}
	)
	
	(method (doVerb)
		(= x ((User curEvent?) x?))
		(= y ((User curEvent?) y?))
		(super doVerb: &rest)
	)
)

(instance robot of Actor
	(properties
		description {your pilot droid}
		lookStr {Your new pilot droid appears to be a bit dinged up, but functional. You hope that he knows more about piloting a spaceship than you do.}
		view 451
		signal $2000
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(5 (Print 702 10))
			(3 (Print 702 11))
			(4
				(if (OneOf theItem 4 15)
					(Print 702 12)
				else
					(super doVerb: theVerb theItem)
				)
			)
			(12 (Print 702 13))
			(11 (Print 702 14))
			(else 
				(super doVerb: theVerb theItem)
			)
		)
	)
)

(instance roboGroop of Grooper
	(properties)
)

(instance zapEgo of Script
	(properties)
	
	(method (doit)
		(if (& (ego signal?) $0400) (self changeState: 3))
		(super doit: &rest)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(if (or (< (++ zapCount) 4) (== curRoomNum 47))
					(ego
						view: 58
						cel: 0
						loop:
							(cond 
								(
									(and
										(<= 0 (ego heading?))
										(<= (ego heading?) 90)
										(< (ego y?) 178)
									)
									0
								)
								((<= (ego heading?) 180) 4)
								((<= (ego heading?) 270) 5)
								(else 1)
							)
						setCycle: End self
					)
				else
					(ego
						view: 29
						cel: 0
						setLoop: (if (< (ego heading?) 180) 2 else 3)
						cycleSpeed: 7
						setCycle: End self
					)
				)
				(zapSound number: 602 loop: 1 play:)
				(zap
					init:
					cel: 0
					x: (ego x?)
					y: (- (ego y?) 32)
					setPri:
						(if (>= (ego loop?) 4)
							(+ (ego priority?) 1)
						else
							(- (ego priority?) 1)
						)
					setCycle: End
				)
			)
			(1
				(if (>= zapCount 4)
					(EgoDead 29 2 0 702 15)
				else
					(ego loop: (+ (ego loop?) 2) cel: 0 setCycle: End self)
				)
			)
			(2
				(ego view: 1)
				(switch (ego loop?)
					(2
						(ego
							posn: (- (ego x?) 18) (+ (ego y?) 3)
							setLoop: 6
							heading: 225
						)
					)
					(3
						(ego
							posn: (+ (ego x?) 18) (+ (ego y?) 3)
							setLoop: 7
							heading: 135
						)
					)
					(6
						(ego
							posn: (- (ego x?) 11) (- (ego y?) 1)
							setLoop: 4
							heading: 325
						)
					)
					(7
						(ego
							posn: (+ (ego x?) 11) (- (ego y?) 1)
							setLoop: 5
							heading: 45
						)
					)
				)
				(ego setCycle: Rev setMotion: MoveFwd 10 self)
			)
			(3
				(= register (ego loop?))
				(NormalEgo 0 1 61)
				(ego loop: register)
				(zap dispose:)
				(if (not (Btst 38))
					(= cycles 8)
				else
					(HandsOn)
					(self dispose:)
				)
			)
			(4
				(Print 702 16)
				(Print 702 17)
				(Bset 38)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance zap of Prop
	(properties
		view 58
		loop 8
		signal $6000
	)
)

(instance zapSound of Sound
	(properties
		number 602
	)
)
