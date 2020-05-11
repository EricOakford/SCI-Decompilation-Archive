;;; Sierra Script 1.0 - (do not remove this comment)
(script# 73)
(include game.sh)
(use Main)
(use Intrface)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	rm73 0
)

(local
	local0
	monkey
)
(instance rm73 of Room
	(properties
		picture 73
		horizon 50
		east 74
		south 74
		west 72
	)
	
	(method (init)
		(Load VIEW 175)
		(super init:)
		(if (== prevRoomNum 74)
			(ego posn: 318 187)
		else
			(ego posn: 2 152)
			((= monkey (Actor new:))
				view: 722
				illegalBits: 0
				posn: 221 168
				init:
			)
		)
		(NormalEgo)
		(ego view: 175 setStep: 2 1 init:)
		(self setRegions: 700 setScript: rm73Script)
	)
)

(instance rm73Script of Script
	(properties)
	
	(method (doit)
		(cond 
			(
				(and
					(== passedQuicksand FALSE)
					(or (== (ego edgeHit?) 2) (== (ego edgeHit?) 3))
				)
				(= passedQuicksand TRUE)
				(theGame changeScore: 5)
				(Print 73 0)
			)
			(
			(and (== currentStatus egoNormal) (& (ego onControl:) $0040)) (self changeState: 3))
		)
		(super doit:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if (!= prevRoomNum 74)
					(monkey
						setStep: 1 1
						setMotion: MoveTo 223 181
						setCycle: EndLoop self
					)
				)
			)
			(1
				(monkey
					setStep: 4 3
					setLoop: 1
					setCycle: Walk
					setMotion: MoveTo 334 189 self
				)
			)
			(2
				(monkey dispose:)
				(Print 73 9)
				(Print 73 10 #at -1 152)
			)
			(3
				(= currentStatus egoStopped)
				(HandsOff)
				(ego
					setLoop:
						(if
						(and (> (ego heading?) 89) (< (ego heading?) 271))
							4
						else
							5
						)
					cycleSpeed: 3
					cel: 0
					setCycle: EndLoop self
				)
				(Print 73 11 #draw)
			)
			(4
				(ego setLoop: 6 setCycle: Forward cycleSpeed: 1)
				(= seconds 5)
			)
			(5
				(Print 73 12)
				(Print 73 13)
				(= currentStatus egoDead)
			)
		)
	)
	
	(method (handleEvent event)
		(if
		(or (!= (event type?) saidEvent) (event claimed?))
			(return)
		)
		(if (Said '/landscape') (Print 73 1))
		(if (Said 'crawl,hop') (Print 73 2))
		(if (Said 'new,stair,disembark,climb,apply/boulder')
			(Print 73 3)
		)
		(if
		(and (cast contains: monkey) (Said '/chimpanzee'))
			(Print 73 4)
		)
		(if (Said '/palm') (Print 73 5))
		(if (Said 'look>')
			(if (Said '/pattern') (Print 73 6))
			(if (Said '/quicksand') (Print 73 7))
			(if (Said '[/forest,carpet,airport]') (Print 73 8))
		)
	)
)
