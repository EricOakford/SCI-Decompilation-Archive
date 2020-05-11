;;; Sierra Script 1.0 - (do not remove this comment)
(script# 19)
(include game.sh)
(use Main)
(use Intrface)
(use Sound)
(use Motion)
(use Game)
(use Invent)
(use Actor)
(use System)

(public
	Room19 0
)

(local
	gEgoViewer
	i
	local2
)
(instance wave1 of Prop
	(properties)
)

(instance wave2 of Prop
	(properties)
)

(instance wave3 of Prop
	(properties)
)

(instance waves of List
	(properties)
)

(instance fallSound of Sound
	(properties)
)

(instance Room19 of Room
	(properties
		picture 19
	)
	
	(method (init)
		(= north 13)
		(= south 25)
		(= east 20)
		(= west 31)
		(= horizon 104)
		(= isIndoors FALSE)
		(ego edgeHit: 0)
		(super init:)
		(if isNightTime (curRoom overlay: 119))
		(= local2
			(+
				(* (- gameHours hourLastMetMinstrel) 60)
				(- gameMinutes minutesLastMetMinstrel)
			)
		)
		(if
		(and ((Inventory at: iLute) ownedBy: 203) (>= local2 3))
			(= whereIsMinstrel
				(/ (= whereIsMinstrel (Random 1 30)) 10)
			)
		)
		(if (== whereIsMinstrel 3)
			((= minstrel (Actor new:))
				view: 174
				loop: 2
				setCel: 0
				illegalBits: 0
				posn: 293 116
				setPri: 14
				init:
			)
		)
		(self setRegions: MINSTREL BEACH WATER GULL)
		(Load VIEW 174)
		(Load VIEW 17)
		(Load VIEW 33)
		(wave1
			view: 666
			loop: 0
			cel: 0
			posn: 183 73
			setPri: 0
			ignoreActors:
			cycleSpeed: 3
			init:
		)
		(wave2
			view: 666
			loop: 1
			cel: 0
			posn: 188 107
			setPri: 0
			ignoreActors:
			cycleSpeed: 3
			init:
		)
		(wave3
			view: 666
			loop: 2
			cel: 0
			posn: 189 152
			setPri: 0
			ignoreActors:
			cycleSpeed: 3
			init:
		)
		(waves add: wave1 wave2 wave3)
		(wave1 setScript: waveActions)
		(Load VIEW 2)
		(Load VIEW 17)
		(Load VIEW 33)
		(if (== (ego view?) 2)
			(= currentStatus egoNormal)
		else
			(= global107 0)
		)
		(ego illegalBits: -16384)
		(switch currentStatus
			(egoNormal
				(switch prevRoomNum
					(25
						(if (> (ego x?) 210)
							(ego x: 293 y: 188)
							(ego illegalBits: -32768 setPri: 14)
							(= global107 11)
						else
							(ego x: 212 y: 188)
							(= global107 0)
						)
					)
					(13
						(if (< (ego x?) 245)
							(= global107 0)
							(ego x: 215 y: (+ horizon (ego yStep?) 1))
						else
							(ego x: 286 y: (+ horizon (ego yStep?) 1))
							(ego setPri: 14 illegalBits: -32768)
							(= global107 11)
						)
					)
					(20
						(ego setPri: 14 illegalBits: -32768)
						(= global107 11)
						(= currentStatus egoNormal)
						(if (< (ego y?) horizon)
							(ego x: 318 y: (+ horizon 2))
						else
							(ego x: 318)
						)
					)
					(else 
						(= global107 11)
						(ego x: 318 y: 160)
						(ego illegalBits: -32768)
						(ego setPri: 14)
					)
				)
			)
			(egoInShallowWater
				(switch prevRoomNum
					(25
						(= global107 0)
						(ego x: 151 y: 188)
					)
					(13
						(= global107 0)
						(ego x: 180 y: (+ horizon (ego yStep?) 1))
					)
				)
			)
			(egoInKneeDeepWater
				(switch prevRoomNum
					(25
						(= global107 0)
						(ego x: 88 y: 188)
					)
					(13
						(= global107 0)
						(ego x: 152 y: (+ horizon (ego yStep?) 1))
					)
				)
			)
			(egoInWaistDeepWater
				(switch prevRoomNum
					(25
						(= global107 0)
						(ego x: 31 y: 188)
					)
					(13
						(= global107 0)
						(ego x: 128 y: (+ horizon (ego yStep?) 1))
					)
				)
			)
			(egoSwimming
				(switch prevRoomNum
					(25 (ego x: 5 y: 188))
					(13
						(ego x: 104 y: (+ horizon (ego yStep?) 1))
					)
					(31
						(if (< (ego y?) horizon)
							(ego x: 1 y: (+ 1 horizon))
						else
							(ego x: 1)
						)
					)
				)
			)
		)
		(ego init:)
		(= gEgoViewer (ego viewer?))
	)
	
	(method (doit &tmp temp0)
		(super doit:)
		(if (== global107 11)
			(cond 
				((ego inRect: 254 104 316 115) (ego setPri: 12))
				(
				(and (!= currentStatus egoFalling) (== (ego edgeHit?) 0)) (ego setPri: 14))
			)
		)
		(if
			(and
				(== (curRoom script?) 0)
				(== global107 11)
				(& (= temp0 (ego onControl: 0)) $31d4)
			)
			(doFall doit:)
			(self
				setScript:
					(cond 
						((& temp0 $2000) fallLmagenta)
						((& temp0 $1000) fallLred)
						((& temp0 $0100) fallGrey)
						((& temp0 $0080) fallLgrey)
						((& temp0 $0010) fallRed)
						((& temp0 $0040) fallBrown)
						((& temp0 $0004) fallGreen)
					)
			)
		)
	)
	
	(method (dispose)
		(waves dispose:)
		(ego setPri: -1)
		(super dispose:)
	)
	
	(method (handleEvent event)
		(if (event claimed?) (return TRUE))
		(return
			(if
			(and (== (event type?) saidEvent) (Said 'look>'))
				(cond 
					((Said '/grass') (Print 19 0))
					((Said '/cliff') (Print 19 1))
					((Said '[<around][/room]') (Print 19 2))
				)
			else
				FALSE
			)
		)
	)
	
	(method (newRoom newRoomNumber)
		(if (cast contains: minstrel)
			(= hourLastMetMinstrel gameHours)
			(= minutesLastMetMinstrel gameMinutes)
		)
		(if (!= currentStatus egoFalling)
			(ego setPri: -1 illegalBits: -32768)
			(super newRoom: newRoomNumber)
		)
	)
)

(instance fallLmagenta of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(= currentStatus egoFalling)
				(ego
					viewer: 0
					illegalBits: 0
					setPri: 11
					yStep: 10
					setLoop: 1
					cel: 0
					view: 17
					posn: (ego x?) (- (ego y?) 4)
					setMotion: MoveTo (ego x?) (+ (ego y?) 40) self
				)
			)
			(1
				(ego view: 33 setLoop: 0 cel: 0)
				(Animate (cast elements?) FALSE)
				(ShakeScreen 10 shakeSDown)
				(= seconds 5)
			)
			(2 (Print 19 3) (= dead TRUE))
		)
	)
)

(instance fallLred of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(= currentStatus egoFalling)
				(ego
					viewer: 0
					setPri: 13
					yStep: 10
					setLoop: 1
					cel: 0
					view: 17
					illegalBits: 0
					setCycle: Forward
					posn: (- (ego x?) 12) (ego y?)
					setMotion: MoveTo 222 170 self
				)
			)
			(1
				(ego view: 33 setLoop: 0 cel: 0)
				(Animate (cast elements?) FALSE)
				(ShakeScreen 10 shakeSDown)
				(= seconds 5)
			)
			(2 (Print 19 4) (= seconds 5))
			(3 (= dead TRUE))
		)
	)
)

(instance fallGrey of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(= currentStatus egoFalling)
				(ego
					viewer: 0
					illegalBits: 0
					setPri: 13
					yStep: 10
					setLoop: 1
					cel: 0
					view: 17
					setCycle: Forward
					posn: (- (ego x?) 8) (ego y?)
					setMotion: MoveTo 263 173 self
				)
			)
			(1
				(ego view: 33 setLoop: 0 cel: 0)
				(Animate (cast elements?) FALSE)
				(ShakeScreen 10 shakeSDown)
				(= seconds 5)
			)
			(2 (Print 19 5) (= seconds 5))
			(3 (= dead TRUE))
		)
	)
)

(instance fallLgrey of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(= currentStatus egoFalling)
				(ego
					viewer: 0
					illegalBits: 0
					setPri: 13
					yStep: 10
					xStep: 8
					setLoop: 1
					cel: 0
					view: 17
					setCycle: Forward
					posn: (ego x?) (- (ego y?) 5)
					setMotion: MoveTo 270 173 self
				)
			)
			(1
				(ego view: 33 setLoop: 0 cel: 0)
				(Animate (cast elements?) FALSE)
				(ShakeScreen 10 shakeSDown)
				(= seconds 5)
			)
			(2 (Print 19 4) (= seconds 5))
			(3 (= dead TRUE))
		)
	)
)

(instance fallRed of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(= currentStatus egoFalling)
				(ego
					viewer: 0
					illegalBits: 0
					setPri: 13
					yStep: 10
					xStep: 8
					setLoop: 1
					cel: 0
					view: 17
					setCycle: Forward
					posn: (ego x?) (- (ego y?) 5)
					setMotion: MoveTo (ego x?) 178 self
				)
			)
			(1
				(ego view: 33 setLoop: 0 cel: 0)
				(Animate (cast elements?) FALSE)
				(ShakeScreen 10 shakeSDown)
				(= seconds 5)
			)
			(2 (Print 19 4) (= seconds 5))
			(3 (= dead TRUE))
		)
	)
)

(instance fallBrown of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(= currentStatus egoFalling)
				(ego
					viewer: 0
					illegalBits: 0
					setPri: 14
					yStep: 10
					setLoop: 1
					cel: 0
					view: 17
					setCycle: Forward
					posn: (- (ego x?) 8) (ego y?)
					setMotion: MoveTo 239 187 self
				)
			)
			(1
				(ego view: 33 setLoop: 0 cel: 0)
				(Animate (cast elements?) FALSE)
				(ShakeScreen 10 shakeSDown)
				(= seconds 3)
			)
			(2 (Print 19 6) (= seconds 5))
			(3 (= dead TRUE))
		)
	)
)

(instance fallGreen of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(= currentStatus egoFalling)
				(ego
					viewer: 0
					illegalBits: 0
					setPri: 14
					yStep: 10
					setLoop: 1
					cel: 0
					view: 17
					setCycle: Forward
					posn: (- (ego x?) 8) (ego y?)
					setMotion: MoveTo 240 219 self
				)
			)
			(1
				(ego view: 33 setLoop: 0 cel: 0)
				(Animate (cast elements?) FALSE)
				(ShakeScreen 10 shakeSDown)
				(= seconds 5)
			)
			(2 (Print 19 7) (= seconds 5))
			(3 (= dead TRUE))
		)
	)
)

(instance waveActions of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= i 0)
				(while (< i (waves size?))
					((View new:)
						view: ((waves at: i) view?)
						loop: ((waves at: i) loop?)
						cel: 0
						setPri: 0
						ignoreActors:
						x: ((waves at: i) x?)
						y: ((waves at: i) y?)
						init:
						addToPic:
						yourself:
					)
					(++ i)
				)
				(= i 0)
				(if howFast
					(self changeState: 1)
				else
					(waves eachElementDo: #addToPic)
				)
			)
			(1
				((waves at: i) cel: 0 show: setCycle: EndLoop self)
			)
			(2
				((waves at: i) hide:)
				(if (< i (- (waves size?) 1))
					(++ i)
				else
					(= i 0)
				)
				(waveActions changeState: 1)
			)
		)
	)
)

(instance doFall of Code
	(properties)
	
	(method (doit)
		(sounds eachElementDo: #stop 0)
		(fallSound number: 51 play:)
		(if (cast contains: minstrel) (minstrel setCycle: 0))
	)
)
