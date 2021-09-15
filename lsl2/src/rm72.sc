;;; Sierra Script 1.0 - (do not remove this comment)
(script# 72)
(include game.sh)
(use Main)
(use Intrface)
(use Motion)
(use Game)
(use User)
(use Actor)
(use System)

(public
	rm72 0
)

(local
	putStickInSnake
	aSnake
	aBurp
)

(enum
	snakeNOTYET
	snakeHERE
	snakeSTICKINMOUTH
	snakeGONE
)

(instance rm72 of Room
	(properties
		picture 72
		horizon 56
		north 71
		east 73
	)
	
	(method (init)
		(super init:)
		(if (== snakeState snakeNOTYET)
			(Load VIEW 701)
			(Load VIEW 174)
			(Load VIEW 172)
			(Load VIEW 28)
			((= aSnake (Actor new:))
				view: 701
				illegalBits: 0
				ignoreActors:
				setLoop: 0
				setCel: 0
				setPri: 14
				cycleSpeed: 1
				posn: 168 97
				stopUpd:
				init:
			)
			((= aBurp (Prop new:))
				view: 172
				ignoreActors:
				setLoop: 4
				setCel: 0
				setPri: 15
				posn: 220 151
				init:
				hide:
			)
		)
		(if (== prevRoomNum 73)
			(ego posn: 316 159)
		else
			(ego posn: 23 57)
		)
		(NormalEgo)
		(ego init:)
		(self setRegions: ISLAND setScript: rm72Script)
	)
)

(instance rm72Script of Script
	(method (doit)
		(super doit:)
		(if
			(and
				(& (ego onControl:) cBLUE)
				(== snakeState snakeNOTYET)
				(== currentStatus egoNORMAL)
			)
			(self changeState: 1)
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(1
				(= currentStatus egoSTOPPED)
				(= snakeState snakeHERE)
				(Print 72 13 #at -1 20)
				(User canControl: FALSE canInput: TRUE)
				(ego setMotion: 0 posn: 142 152 setLoop: 2)
				(aSnake setCycle: EndLoop self)
			)
			(2
				(if (!= putStickInSnake TRUE)
					(Print 72 14 #at -1 20 #draw)
				)
				(aSnake
					posn: 130 144
					setLoop: 1
					cel: 0
					setCycle: EndLoop self
				)
			)
			(3
				(ego hide:)
				(User canInput: FALSE)
				(if putStickInSnake
					(self changeState: 12)
				else
					(aSnake
						view: 172
						setLoop: 0
						posn: 138 152
						cycleSpeed: 2
						cel: 0
						setCycle: EndLoop self
					)
				)
			)
			(4
				(aSnake
					setLoop: 1
					posn: 140 152
					cel: 0
					cycleSpeed: 0
					setCycle: Forward
				)
				(= seconds 5)
			)
			(5
				(aSnake
					setLoop: 2
					cycleSpeed: 3
					cel: 0
					setCycle: EndLoop self
				)
			)
			(6
				(aSnake cycleSpeed: 2 setLoop: 3 setCycle: Forward)
				(= seconds 3)
			)
			(7
				(Print 72 15)
				(= seconds 3)
			)
			(8
				(aBurp show: setCycle: EndLoop self)
			)
			(9
				(= cycles 10)
			)
			(10
				(aBurp setCycle: BegLoop self)
			)
			(11
				(aBurp dispose:)
				(Print 72 16 #draw)
				(= currentStatus egoDYING)
			)
			(12
				(= snakeState snakeSTICKINMOUTH)
				(aSnake
					view: 174
					setLoop: 0
					cycleSpeed: 2
					posn: 139 145
					cel: 0
					setCycle: EndLoop self
				)
				(Print 72 17 #at -1 20 #draw #icon 28 0 0)
			)
			(13
				(= seconds 3)
			)
			(14
				(aSnake
					view: 174
					setLoop: 1
					cycleSpeed: 3
					cel: 0
					setCycle: EndLoop self
				)
				(theGame changeScore: 10)
				(Print 72 18 #at -1 20 #draw)
			)
			(15
				(ego posn: 146 159 setLoop: 0 show: put: 28 -1)
				(aSnake
					setPri: -1
					setLoop: 2
					posn: 142 114
					cel: 0
					setCycle: EndLoop self
				)
			)
			(16
				(aSnake dispose:)
				(= snakeState snakeGONE)
				(NormalEgo)
			)
		)
	)
	
	(method (handleEvent event)
		(if (or (!= (event type?) saidEvent) (event claimed?))
			(return)
		)
		(if
			(or
				(Said 'get/stick<off')
				(Said 'get/stick<ready')
				(Said '(get<off),apply,stick,carry,insert,conceal/stick')
			)
			(cond 
				((not (ego has: iStoutStick))
					(DontHave)
				)
				((!= currentStatus egoSTOPPED)
					(NotClose)
				)
				(else
					(Ok)
					(Print 72 0 #at -1 20)
					(= putStickInSnake TRUE)
				)
			)
		)
		(if (Said 'apply,swing,get/landscape')
			(Print 72 1)
		)
		(if (not snakeState)
			(if (Said 'attack/anaconda/stick')
				(if (ego has: iStoutStick)
					(Print 72 2)
				else
					(DontHave)
				)
			)
			(if (Said 'attack/anaconda')
				(Print 72 3)
			)
			(if (Said 'crawl')
				(Print 72 4)
			)
		)
		(if (Said 'look>')
			(if (Said '/bush,palm,leaf')
				(if (== snakeState snakeGONE)
					(Print 72 5)
				else
					(Print 72 6)
				)
			)
			(if (Said '/carpet,dirt,carpet')
				(Print 72 7)
			)
			(if (Said '/ear,art,lip')
				(Print 72 8)
			)
			(if (Said '/anaconda')
				(if (== snakeState snakeGONE)
					(Print 72 9)
				else
					(Print 72 10)
				)
			)
			(if (Said '[/airport,forest]')
				(Print 72 11)
				(if (== snakeState snakeHERE)
					(Print 72 12)
				)
			)
		)
	)
)
