;;; Sierra Script 1.0 - (do not remove this comment)
(script# 87)
(include game.sh)
(use Main)
(use Intrface)
(use Avoider)
(use Sound)
(use Motion)
(use Game)
(use User)
(use Actor)
(use System)

(public
	Room87 0
)

(local
	h1
	h2
	h3
	door2
	door3
	door4
	door1
	local7
)
(instance theMusic of Sound
	(properties)
)

(instance doorSound of Sound
	(properties
		number 300
	)
)

(instance Room87 of Room
	(properties
		picture 87
		style (| BLACKOUT IRISOUT)
	)
	
	(method (init)
		(Load VIEW 145)
		(Load VIEW 141)
		(Load VIEW 633)
		(Load VIEW 608)
		(Load VIEW 147)
		(self setRegions: LOLOTTE)
		(super init:)
		(NotifyScript LOLOTTE 0)
		(theMusic number: 41 loop: 0 init:)
		((= door1 (Prop new:))
			view: 608
			posn: 140 117
			cel: 0
			init:
			stopUpd:
		)
		((= door2 (Prop new:))
			posn: 24 64
			view: 633
			cel: 2
			init:
			setCycle: Forward
		)
		((= door3 (Prop new:))
			posn: 32 74
			view: 633
			cel: 0
			init:
			setCycle: Forward
		)
		((= door4 (Prop new:))
			posn: 42 64
			view: 633
			cel: 1
			init:
			setCycle: Forward
		)
		(if lolotteAlive
			((= h1 (Actor new:))
				view: 145
				loop: 4
				posn: 200 122
				init:
				setScript: henchChase
			)
		else
			((= h1 (Actor new:))
				view: 147
				loop: 1
				cel: 0
				posn: 272 153
				init:
			)
			((= h2 (Actor new:))
				view: 147
				loop: 2
				cel: 0
				posn: 250 157
				init:
			)
			((= h3 (Actor new:))
				view: 147
				loop: 2
				cel: 0
				posn: 212 158
				init:
			)
			(ego observeControl: 1024)
			(h1 setScript: circleJerk)
		)
		(if (or (== prevRoomNum 88) (== prevRoomNum 0))
			(ego posn: 261 133 view: 4 xStep: 4 yStep: 2 init:)
		)
		(if (== prevRoomNum 86)
			(ego posn: 25 142 view: 4 xStep: 4 yStep: 2 init:)
		)
		(if (== prevRoomNum 84)
			(ego posn: 131 124 view: 4 xStep: 4 yStep: 2 init:)
		)
	)
	
	(method (doit)
		(super doit:)
		(if (& (ego onControl: 0) $0040) (curRoom newRoom: 86))
		(if (& (ego onControl: 0) $0020) (curRoom newRoom: 84))
		(if (& (ego onControl: 0) $0010) (curRoom newRoom: 88))
	)
	
	(method (dispose)
		(timers eachElementDo: #dispose 84)
		(super dispose:)
	)
	
	(method (handleEvent event)
		(return
			(cond 
				((event claimed?) (return TRUE))
				((== (event type?) saidEvent)
					(cond 
						(
							(or
								(Said 'look[<around][/!*]')
								(Said 'look[<around]/room,castle,hall')
							)
							(Print 87 0)
						)
						((Said 'look>')
							(cond 
								((Said '/goon') (Print 87 1 #at -1 10))
								((Said '/door') (Print 87 2))
								((Said '/table') (Print 87 3))
								((Said '/candelabra,candle') (Print 87 4))
								((Said '/wall') (Print 87 5))
								((or (Said '/dirt') (Said '<down')) (Print 87 6))
								(else (event claimed: FALSE))
							)
						)
						((Said '/man,goon') (if lolotteAlive (Print 87 7) else (Print 87 8)))
						((Said 'get/candelabra,candle') (Print 87 9 #at -1 10))
						((Said 'bang/door')
							(if (ego inRect: 111 118 140 123)
								(Print 87 10 #at -1 10)
							else
								(Print 800 1)
							)
						)
						((Said 'open/door')
							(cond 
								((!= (door1 cel?) 0) (Print 87 11 #at -1 10))
								((not (ego inRect: 111 118 140 123)) (Print 800 1))
								(else (door1 setCycle: EndLoop doDoor) (doorSound play:))
							)
						)
						((Said 'close/door') (Print 87 12))
						((Said 'unlatch/door')
							(if (ego inRect: 111 118 140 123)
								(Print 87 13)
							else
								(Print 800 1)
							)
						)
						((Said '/goon,man>')
							(cond 
								((Said 'converse') (Print 87 14 #at -1 10))
								((Said 'get,capture') (Print 87 15 #at -1 10))
								((Said 'kiss') (Print 87 16 #at -1 10))
								((Said 'deliver') (Print 87 17 #at -1 10))
							)
						)
					)
				)
			)
		)
	)
)

(instance doDoor of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(door1 stopUpd:)
				(curRoom newRoom: 84)
			)
		)
	)
)

(instance henchChase of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(h1 loop: 2 setCycle: EndLoop self)
			)
			(1
				(h1 loop: 0 setCycle: EndLoop self)
			)
			(2
				(= henchChasingEgo TRUE)
				(theMusic number: 41 loop: 10 play:)
				(h1
					view: 141
					setAvoider: Avoider
					setCycle: Walk
					setMotion: Chase ego 15 self
				)
			)
			(3
				(User canControl: FALSE canInput: FALSE)
				(ego moveSpeed: 0 setMotion: 0)
				(theMusic number: 42 loop: 1 play:)
				(= seconds 4)
			)
			(4
				(= inCutscene TRUE)
				(curRoom newRoom: 81)
			)
		)
	)
)

(instance circleJerk of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				((ScriptID 0 4) setReal: self 2)
			)
			(1
				(h1 setCycle: EndLoop)
				(h2 setCycle: EndLoop)
				(h3 setCycle: EndLoop)
				((ScriptID 0 4) setReal: self 5)
			)
			(2
				(h2 setCycle: BegLoop)
				(h1 setCycle: BegLoop)
				(h3 setCycle: BegLoop self)
			)
			(3 (client setScript: 0))
		)
	)
)
