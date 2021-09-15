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
			(aMonkey
				illegalBits: 0
				init:
			)
		)
		(NormalEgo)
		(ego view: 175 setStep: 2 1 init:)
		(self setRegions: ISLAND setScript: rm73Script)
	)
)

(instance rm73Script of Script
	(method (doit)
		(cond 
			(
				(and
					(== passedQuicksand FALSE)
					(or (== (ego edgeHit?) EAST) (== (ego edgeHit?) SOUTH))
				)
				(= passedQuicksand TRUE)
				(theGame changeScore: 5)
				(Print 73 0)
			)
			((and (== currentStatus egoNORMAL) (& (ego onControl:) cBROWN))
				(self changeState: 3)
			)
		)
		(super doit:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if (!= prevRoomNum 74)
					(aMonkey
						setStep: 1 1
						setMotion: MoveTo 223 181
						setCycle: EndLoop self
					)
				)
			)
			(1
				(aMonkey
					setStep: 4 3
					setLoop: 1
					setCycle: Walk
					setMotion: MoveTo 334 189 self
				)
			)
			(2
				(aMonkey dispose:)
				(Print 73 9)
				(Print 73 10 #at -1 130)
			)
			(3
				(= currentStatus egoSTOPPED)
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
				(= currentStatus egoDYING)
			)
		)
	)
	
	(method (handleEvent event)
		(if (or (!= (event type?) saidEvent) (event claimed?))
			(return)
		)
		(if (Said '/landscape')
			(Print 73 1)
		)
		(if (Said 'crawl,hop')
			(Print 73 2)
		)
		(if (Said 'new,stair,disembark,climb,apply/boulder')
			(Print 73 3)
		)
		(if (and (cast contains: aMonkey) (Said '/chimpanzee'))
			(Print 73 4)
		)
		(if (Said '/palm')
			(Print 73 5)
		)
		(if (Said 'look>')
			(if (Said '/pattern')
				(Print 73 6)
			)
			(if (Said '/quicksand')
				(Print 73 7)
			)
			(if (Said '[/forest,carpet,airport]')
				(Print 73 8)
			)
		)
	)
)

(instance aMonkey of Actor
	(properties
		y 168
		x 221
		view 722
	)
)
