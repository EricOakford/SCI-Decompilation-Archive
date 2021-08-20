;;; Sierra Script 1.0 - (do not remove this comment)
(script# 85)
(include game.sh)
(use Main)
(use Intrface)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	Room85 0
)

(local
	curControl
	local1
	attackingGuard
)
(instance guard of Prop
	(properties)
)

(instance guard2 of Prop
	(properties)
)

(instance force of View
	(properties)
)

(instance blast of Actor
	(properties)
)

(instance Room85 of Room
	(properties
		picture 85
	)
	
	(method (init)
		(= north (= west (= south 80)))
		(Load VIEW 123)
		(Load VIEW 593)
		(Load VIEW 134)
		(Load VIEW 92)
		(super init:)
		(NormalEgo)
		(guard view: 124 loop: 4 cel: 0 posn: 132 153 init:)
		(guard2 view: 124 loop: 4 cel: 0 posn: 221 122 init:)
		(switch prevRoomNum
			(81
				(HandsOff)
				(ego view: 0 posn: 324 166 init:)
				(curRoom setScript: Actions)
			)
			(86
				(HandsOff)
				(ego view: 0 posn: 123 169 setPri: 4 init:)
				(curRoom setScript: Actions)
				(Actions changeState: 2)
			)
		)
		(if (ego has: iCoveralls) (ego view: 113))
	)
	
	(method (doit)
		(super doit:)
		(if (!= curRoomNum newRoomNum) (return))
		(if (== (Actions state?) 1)
			(if
				(and
					(not isHandsOff)
					(or
						(> (ego x?) 313)
						(and (> (ego y?) 186) (> (ego x?) 174))
					)
					(== (ego script?) 0)
				)
				(ego setScript: turnVisible)
			)
			(= curControl (ego onControl: 0))
			(-- beltTimer)
			(if
				(and
					(== beltState beltTURNEDON)
					(not isHandsOff)
					(<= beltTimer 0)
				)
				(ego setScript: turnVisible)
				(= beltState beltDEPLETED)
			)
			(cond 
				((& curControl $0004) (Actions changeState: 5))
				((& curControl $1000) (Actions changeState: 6))
				((& curControl $2000) (Actions changeState: 7))
				((& curControl $0040) (curRoom setScript: walkDown))
			)
			(if (== egoInvisible 0) (Actions changeState: 8))
		)
	)
	
	(method (handleEvent event)
		(if (event claimed?) (return TRUE))
		(return
			(if (== (event type?) saidEvent)
				(cond 
					((Said 'look>')
						(cond 
							(
								(or
									(Said '/area')
									(Said '/around')
									(Said '[<around][/!*]')
								)
								(Print 85 0)
							)
							((Said '/guard') (Print 85 1))
							((Said '/beam') (Print 85 2))
							(else (Print 85 3) (event claimed: TRUE))
						)
					)
					(
					(or (Said 'turn<off/belt') (Said 'deactivate/belt')) (Print 85 4))
					(
						(or
							(Said 'attack,beat,converse,kiss/guard,man,flunky')
							(Said 'get/gun')
						)
						(Print 85 5)
					)
					((Said 'turn<off/beam') (Print 85 6))
				)
			else
				FALSE
			)
		)
	)
	
	(method (newRoom newRoomNumber)
		(if (and (>= (ego y?) 189) (<= (ego x?) 188))
			(++ local1)
		else
			(super newRoom: newRoomNumber)
		)
	)
)

(instance Actions of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if (== beltState beltTURNEDON) (ego view: 123))
				(ego setMotion: MoveTo 303 166 self)
			)
			(1
				(HandsOn)
				(curRoom east: 80)
			)
			(2
				(ego
					setLoop: 0
					illegalBits: 0
					ignoreActors:
					setMotion: MoveTo 159 129 self
				)
			)
			(3
				(ego setPri: -1 setMotion: MoveTo 204 150 self)
			)
			(4
				(NormalEgo)
				(self changeState: 1)
			)
			(5
				(HandsOff)
				(force
					view: 124
					setLoop: 1
					setCel: 0
					setPri: 1
					ignoreActors:
					posn: 253 116
					init:
					addToPic:
				)
				(ego view: 124 setLoop: 3 setCycle: Forward)
				(= state 13)
				(= seconds 7)
			)
			(6
				(HandsOff)
				(force
					view: 124
					setLoop: 1
					setCel: 1
					setPri: 12
					ignoreActors:
					posn: 75 174
					init:
					addToPic:
				)
				(ego view: 124 setLoop: 3 setCycle: Forward)
				(= state 13)
				(= seconds 7)
			)
			(7
				(HandsOff)
				(force
					view: 124
					setLoop: 1
					setCel: 2
					setPri: 14
					ignoreActors:
					posn: 99 187
					init:
					addToPic:
				)
				(ego view: 124 setLoop: 2 setCycle: Forward)
				(= state 13)
				(= seconds 7)
			)
			(8
				(= seconds 1)
				(ego view: (if (ego has: 12) 113 else 0) loop: 2)
			)
			(9
				(HandsOff)
				(RedrawCast)
				(theMusic number: 44 loop: 1 priority: 2 play:)
				(cond 
					((ego inRect: 24 41 125 184)
						(guard setLoop: 5 cel: 0 setCycle: EndLoop self)
						(= attackingGuard guard)
					)
					((> (ego y?) 142) (guard setCycle: EndLoop self) (= attackingGuard guard))
					((ego inRect: 107 118 209 147)
						(guard2 setLoop: 5 cel: 0 setCycle: EndLoop self)
						(= attackingGuard guard2)
					)
					(else (guard2 setCycle: EndLoop self) (= attackingGuard guard2))
				)
			)
			(10
				(HandsOff)
				(blast
					view: 593
					setLoop: (if (== (attackingGuard loop?) 4) 6 else 7)
					cel: 0
					setCycle: EndLoop
					setStep: 30 10
					ignoreActors:
					illegalBits: 0
					setPri: (ego priority?)
					x:
						(if (== (attackingGuard loop?) 4)
							(+ (attackingGuard x?) 30)
						else
							(- (attackingGuard x?) 30)
						)
					y: (- (attackingGuard y?) 18)
					init:
				)
				(RedrawCast)
				(blast setMotion: MoveTo (ego x?) (- (ego y?) 18) self)
			)
			(11
				(blast dispose:)
				(ego
					setLoop: (if (ego has: 12) 5 else 2)
					view: 92
					cel: 0
					setCycle: Forward
				)
				(= seconds 7)
			)
			(12 (EgoDead 901 0 7 99))
			(14
				(Print 85 7)
				(self changeState: 16)
			)
			(16 (EgoDead 901 0 11 18))
		)
	)
)

(instance turnVisible of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego setMotion: 0 view: 134 setLoop: 4 setCycle: Forward)
				(RedrawCast)
				(= seconds 2)
			)
			(1
				(Print 85 8)
				(ego setLoop: 3)
				(= seconds 2)
			)
			(2
				(Print 85 9)
				(ego setLoop: 1)
				(= seconds 3)
			)
			(3
				(ego setCycle: 0 setCel: 0)
				(= egoInvisible FALSE)
			)
		)
	)
)

(instance walkDown of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego
					setPri: 4
					setLoop: 1
					illegalBits: 0
					ignoreActors:
					setMotion: MoveTo 123 169 self
				)
			)
			(1 (curRoom newRoom: 86))
		)
	)
)
