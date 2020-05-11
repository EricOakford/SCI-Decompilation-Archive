;;; Sierra Script 1.0 - (do not remove this comment)
(script# 40)
(include game.sh)
(use Main)
(use Intrface)
(use Extra)
(use Sound)
(use Motion)
(use Game)
(use User)
(use Actor)
(use System)

(public
	rm40 0
)

(local
	local0
	searchingForShortcut
	egoJungleView
	flower
	peacock
	plant
	pottedPlant
	cockatoo
	parrot
)
(instance theSound of Sound
	(properties
		number 8
		priority -33
		loop -1
	)
)

(instance rm40 of Room
	(properties
		picture 40
		horizon 5
	)
	
	(method (init)
		(cond 
			((== currentEgoView 149) (= egoJungleView 146))
			((== currentEgoView 150) (= egoJungleView 147))
			((== currentEgoView 151) (= egoJungleView 157))
			(else (= egoJungleView 145))
		)
		(Load VIEW egoJungleView)
		(Load VIEW 415)
		(Load VIEW 700)
		(Load VIEW 414)
		(Load VIEW 412)
		(Load SOUND 113)
		(Load SOUND 106)
		(Load SOUND 8)
		(super init:)
		(theSound play:)
		(++ jungleMazeTimes)
		(if global111 (= jungleMazeTimes 257))
		(= flower ((inventory at: iFlower) ownedBy: curRoomNum))
		((= plant (View new:))
			view: 415
			ignoreActors:
			setLoop: 1
			setCel: flower
			posn: 139 156
			stopUpd:
			init:
		)
		((= pottedPlant (Actor new:))
			view: 415
			ignoreActors:
			setLoop: 0
			setCel: 0
			setStep: 1 1
			posn: 103 160
			setPri: 12
			init:
		)
		((= cockatoo (Extra new:))
			view: 700
			ignoreActors:
			setLoop: 0
			posn: 279 58
			setPri: 15
			minPause: 22
			maxPause: 44
			minCycles: 11
			maxCycles: 22
			init:
		)
		((= parrot (Extra new:))
			view: 700
			ignoreActors:
			setLoop: 1
			posn: 140 49
			setPri: 15
			minPause: 22
			maxPause: 55
			minCycles: 11
			maxCycles: 22
			init:
		)
		(if (== egoJungleView 145)
			((= peacock (Actor new:))
				view: 412
				ignoreActors:
				illegalBits: 0
				setLoop: 1
				setCycle: Walk
				posn: 222 1221
				init:
			)
		)
		(NormalEgo 0)
		(ego ignoreActors: illegalBits: 0 posn: 44 188 init:)
		(User canControl: FALSE)
		(= currentStatus egoInJungleMaze)
		(self setRegions: 400 setScript: rm40Script)
	)
)

(instance rm40Script of Script
	(properties)
	
	(method (doit)
		(super doit:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego posn: 16 170 setMotion: MoveTo 91 171 self)
			)
			(1
				(if (== jungleMazeTimes 257) (= state 62))
				(= cycles 15)
			)
			(2
				(ego setLoop: 3)
				(= cycles 15)
			)
			(3
				(ego setLoop: 2)
				(= cycles 15)
			)
			(4
				(ego setLoop: 0)
				(= cycles 15)
			)
			(5
				(ego setLoop: -1 setMotion: MoveTo 19 175 self)
			)
			(6
				(= cycles 30)
				(if searchingForShortcut (= state 62))
			)
			(7
				(ego posn: 249 159 setMotion: MoveTo 157 160 self)
			)
			(8 (= cycles 15))
			(9
				(ego setLoop: 3)
				(= cycles 15)
			)
			(10
				(ego setLoop: 2)
				(= cycles 15)
			)
			(11
				(ego setLoop: 0)
				(= cycles 15)
			)
			(12
				(ego setLoop: -1 setMotion: MoveTo 324 160 self)
			)
			(13
				(= cycles 30)
				(if (or searchingForShortcut (> jungleMazeTimes 8)) (= state 62))
			)
			(14
				(ego
					view: egoJungleView
					setLoop: -1
					setPri: -1
					setStep: 2 1
					posn: 228 135
					setMotion: MoveTo 180 136 self
				)
			)
			(15 (= cycles 15))
			(16
				(ego setLoop: 3)
				(= cycles 15)
			)
			(17
				(ego setLoop: 2)
				(= cycles 15)
			)
			(18
				(ego setLoop: 0)
				(= cycles 15)
			)
			(19
				(ego setLoop: -1 setMotion: MoveTo 318 136 self)
			)
			(20 (= cycles 30))
			(21
				(ego posn: 119 142 setMotion: MoveTo 2 143 self)
			)
			(22
				(= cycles 30)
				(if (or (!= egoJungleView 145) searchingForShortcut (> jungleMazeTimes 7))
					(= state 62)
				)
			)
			(23
				(ego
					setLoop: 4
					setPri: 12
					posn: 117 25
					setCel: 255
					setCycle: Reverse
					setMotion: MoveTo 117 63 self
				)
			)
			(24
				(ego setCycle: Walk)
				(= cycles 30)
			)
			(25
				(ego setCycle: Forward setMotion: MoveTo 117 26 self)
			)
			(26
				(= cycles 50)
				(if (or searchingForShortcut (> jungleMazeTimes 6)) (= state 62))
			)
			(27
				(ego
					setLoop: 7
					cel: 0
					posn: 243 140
					setPri: 15
					setCycle: EndLoop self
				)
			)
			(28 (= cycles 30))
			(29 (ego setCycle: BegLoop self))
			(30
				(ego hide:)
				(= cycles 30)
				(if (or searchingForShortcut (> jungleMazeTimes 5)) (= state 62))
			)
			(31
				(pottedPlant setMotion: MoveTo 103 151)
				(ego
					setLoop: 5
					cel: 0
					posn: 102 163
					setPri: 14
					show:
					cycleSpeed: 0
					setCycle: EndLoop self
				)
			)
			(32 (= cycles 30))
			(33
				(ego setCycle: CycleTo 5 -1 self)
			)
			(34
				(pottedPlant setMotion: MoveTo 103 161)
				(ego setCycle: BegLoop self)
			)
			(35
				(ego hide:)
				(= cycles 30)
				(if (or searchingForShortcut (> jungleMazeTimes 4)) (= state 62))
			)
			(36
				(ego
					setLoop: 6
					cel: 0
					posn: 218 88
					setPri: 10
					show:
					setCycle: CycleTo 5 1 self
				)
			)
			(37 (= cycles 7))
			(38 (ego setCycle: EndLoop self))
			(39
				(ego setCel: setStep: 1 6 setMotion: MoveTo 218 143 self)
			)
			(40
				(= cycles 30)
				(if (or searchingForShortcut (> jungleMazeTimes 3)) (= state 62))
			)
			(41
				(peacock
					view: 412
					setLoop: 1
					setStep: 1 1
					posn: 177 135
					setCycle: Walk
					setMotion: MoveTo 162 138 self
				)
			)
			(42
				(peacock setMotion: MoveTo 162 155 self)
			)
			(43
				(peacock setLoop: 0 cel: 0 setCycle: EndLoop self)
			)
			(44 (= cycles 30))
			(45
				(ego
					loop: 2
					setLoop: -1
					setPri: -1
					setCycle: Walk
					setStep: 2 1
					posn: 163 151
				)
				(peacock setCycle: BegLoop self)
			)
			(46
				(peacock
					setLoop: 1
					setCycle: Walk
					setMotion: MoveTo 162 196 self
				)
			)
			(47
				(ego setMotion: MoveTo 163 147 self)
			)
			(48
				(ego setMotion: MoveTo 0 147 self)
				(peacock hide:)
			)
			(49
				(= cycles 30)
				(if (or searchingForShortcut (> jungleMazeTimes 2)) (= state 62))
			)
			(50
				(ego
					view: egoJungleView
					setLoop: -1
					setPri: -1
					posn: 321 146
					setCycle: Walk
					setMotion: MoveTo 214 147 self
				)
			)
			(51
				(ego setLoop: 0 setMotion: MoveTo 170 147 self)
			)
			(52
				(peacock
					view: 414
					setLoop: -1
					setPri: -1
					setCycle: Walk
					setStep: 2 1
					posn: 249 152
					setMotion: MoveTo 214 153 self
					show:
				)
				(theSound dispose:)
				(theSound number: 113 loop: 1 play:)
			)
			(53
				(ego setLoop: -1 setMotion: MoveTo 117 147 self)
			)
			(54
				(peacock setMotion: MoveTo 170 153 self)
			)
			(55
				(peacock loop: 0)
				(theSound dispose:)
				(theSound number: 106 loop: 1 play:)
				(= cycles 15)
			)
			(56
				(peacock loop: 1)
				(= cycles 15)
			)
			(57
				(peacock setMotion: MoveTo 333 153 self)
				(if (or searchingForShortcut (> jungleMazeTimes 1)) (= state 62))
			)
			(58
				(theSound dispose:)
				(theSound number: 8 loop: -1 play:)
				(ego
					loop: 7
					setLoop: -1
					cel: 0
					posn: 264 16
					setPri: 15
					cycleSpeed: 1
					setCycle: EndLoop self
				)
			)
			(59 (= cycles 30))
			(60 (ego setCycle: BegLoop self))
			(61
				(ego cycleSpeed: 1 hide:)
				(= cycles 30)
			)
			(62
				(= cycles 30)
				(= seconds 0)
			)
			(63
				(cond 
					((== jungleMazeNextRoom 0) (= jungleMazeNextRoom 43))
					((> (++ jungleMazeNextRoom) 45) (= jungleMazeNextRoom 42))
				)
				(if (!= egoJungleView 145)
					(Print 40 15)
				else
					(Print 40 16)
					(Print 40 17 #at -1 152)
					(switch jungleMazeNextRoom
						(42 (Print 40 18))
						(43 (Print 40 19))
						(44 (Print 40 20))
						(45 (Print 40 21))
					)
				)
				(curRoom newRoom: jungleMazeNextRoom)
			)
		)
	)
	
	(method (handleEvent event)
		(if
		(or (!= (event type?) saidEvent) (event claimed?))
			(return)
		)
		(if (Said 'look>')
			(if (Said '/carpet') (Print 40 0))
			(if (and flower (Said '/flower')) (Print 40 1))
			(if (Said '<below/bush') (Print 40 2))
			(if (Said '/feather') (Print 40 3))
			(if (Said '/bird') (Print 40 4))
			(if (Said '/agent')
				(if (and (>= state 50) (<= state 57))
					(Print 40 5)
				else
					(Print 40 6)
				)
			)
			(if (Said '[/airport,palm]') (Print 40 7))
		)
		(if
		(and (>= state 50) (<= state 57) (Said 'call/man'))
			(Print 40 8)
		)
		(if (Said 'climb/palm') (Print 40 9))
		(if (Said 'get/coconuts') (Print 40 10))
		(if (Said 'get/feather,agent,feather,bird')
			(Print 40 11)
		)
		(if
			(or
				(Said 'get<off')
				(Said 'done,disembark,disembark')
				(Said 'explore/disembark')
			)
			(if (> jungleMazeTimes 1)
				(Print 40 12)
				(= searchingForShortcut TRUE)
			else
				(Print 40 13)
			)
		)
		(if (Said 'get,cut,get/flower')
			(cond 
				((!= currentStatus egoInJungleMaze) (PrintNotNow))
				((not flower) (PrintAlreadyTookIt))
				((not (ego inRect: 128 150 160 167)) (PrintNotCloseEnough))
				(else
					(ego get: iFlower)
					(= flower 0)
					(theGame changeScore: 3)
					(plant setCel: flower stopUpd:)
					(Print 40 14 #at -1 20 #draw)
				)
			)
		)
	)
)
