;;; Sierra Script 1.0 - (do not remove this comment)
(script# 37)
(include game.sh)
(use Main)
(use Intrface)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	rm37 0
)

(local
	local0
	aBarber
	aChair
	talkedToBarber
)
(instance rm37 of Room
	(properties
		picture 125
		south 31
	)
	
	(method (init)
		(Load VIEW currentEgoView)
		(Load VIEW 232)
		(Load VIEW 303)
		(Load VIEW 302)
		(super init:)
		((= aChair (View new:))
			view: 232
			loop: 1
			cel: 0
			posn: 164 118
			setPri: 8
			ignoreActors:
			stopUpd:
			init:
		)
		((= aBarber (Actor new:))
			view: 303
			setLoop: 0
			posn: 134 145
			setCycle: Walk
			observeControl: cYELLOW cWHITE
			moveSpeed: 6
			cycleSpeed: 3
			setMotion: Wander
			init:
		)
		(NormalEgo)
		(ego posn: 160 159 init:)
		(self setRegions: 7 300 setScript: rm37Script)
	)
)

(instance rm37Script of Script
	(properties)
	
	(method (doit)
		(super doit:)
		(if (& (ego onControl:) $0002)
			(if (== currentEgoView 133)
				(if (< state 18) (self changeState: 18))
			else
				(curRoom newRoom: 31)
			)
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if ((inventory at: iWig) ownedBy: curRoomNum)
					(= seconds 10)
				)
			)
			(1
				(if (not talkedToBarber)
					(Print 37 9)
					(= seconds (Random 12 25))
					(= state 0)
				)
			)
			(2
				(= seconds (= cycles 0))
				(HandsOff)
				(Print 37 10 #at -1 20)
				(Print 37 11 #at -1 20)
				(Print 37 12 #at -1 20)
				(ego
					illegalBits: 0
					ignoreActors:
					setMotion: MoveTo 157 120 self
				)
				(aBarber
					setLoop: 1
					moveSpeed: 0
					cycleSpeed: 0
					setCycle: EndLoop
					setMotion: 0
				)
			)
			(3
				(aChair hide:)
				(ego
					view: 232
					setLoop: 2
					cel: 0
					posn: 167 115
					setCycle: EndLoop self
				)
			)
			(4
				(aBarber setLoop: 2 setCycle: Forward)
				(= seconds 3)
			)
			(5
				(aBarber setLoop: 3 setCycle: EndLoop self)
			)
			(6 (= seconds 3))
			(7
				(aBarber
					setLoop: 4
					setCycle: Walk
					setMotion: MoveTo 166 117 self
					illegalBits: 0
				)
			)
			(8
				(aBarber setLoop: 5 setCycle: EndLoop self)
				(Print 37 13 #at -1 20 #draw)
				(Print 37 14 #at -1 20)
			)
			(9
				(aBarber setLoop: 6 setCycle: Forward)
				(Print 37 15 #at -1 20 #draw)
				(= seconds 4)
			)
			(10
				(aBarber setLoop: 7 setCycle: EndLoop self)
			)
			(11
				(aBarber setLoop: 8 setCycle: Forward)
				(= seconds 4)
			)
			(12
				(ego view: 302 setLoop: 0 cel: 0)
				(aBarber setLoop: 9 setCycle: CycleTo 6 1 self)
			)
			(13
				(aBarber setCycle: EndLoop self setMotion: MoveTo 176 118)
			)
			(14
				(Print 37 16 #at -1 20)
				(Print 37 17 #at -1 152)
				(if (> filthLevel 4)
					(Print 37 18 #at -1 20)
				else
					(Print 37 19 #at -1 20)
				)
				(Print 37 20 #at -1 20)
				(ego get: iWig)
				(theGame changeScore: 3)
				(= seconds 3)
			)
			(15
				(ego cycleSpeed: 1 setCycle: EndLoop self)
			)
			(16
				(ego posn: 159 119)
				(= currentEgoView 133)
				(NormalEgo 3)
				(aChair show:)
				(HandsOff)
				(= seconds 3)
			)
			(17
				(ego setMotion: MoveTo 159 199 self)
			)
			(18
				(Print (Format @str 37 21 tritePhrase))
				(HandsOff)
				(ego setLoop: 3)
				(= cycles 20)
			)
			(19
				(Print 37 22 #at -1 20)
				(ego
					view: 302
					cycleSpeed: 2
					setLoop: 1
					setCel: 0
					setCycle: EndLoop self
				)
			)
			(20
				(= currentEgoView 100)
				(NormalEgo 2)
			)
		)
	)
	
	(method (handleEvent event)
		(if
		(or (!= (event type?) saidEvent) (event claimed?))
			(return)
		)
		(if (Said 'look>')
			(if (Said '/cup,cup,lagoon,fluid') (Print 37 0))
			(if (Said '/up,overhead') (Print 37 1))
			(if (Said '[/craft,cloud]') (Print 37 2))
			(if (Said '/man') (Print 37 3))
		)
		(if
			(or
				(Said 'bath,fix,ok,cut')
				(Said '(get<in)/barstool')
				(Said 'get/haircut,(cut<hair)')
			)
			(= talkedToBarber TRUE)
			(cond 
				((not (ego inRect: 148 117 180 127)) (Print 37 4))
				((not ((inventory at: iWig) ownedBy: curRoomNum)) (Print 37 5) (Print 37 6))
				((== currentStatus egoSITTING) (YouAre))
				(else (self changeState: 2))
			)
		)
		(if (Said 'call/man')
			(= talkedToBarber TRUE)
			(if (not ((inventory at: iWig) ownedBy: curRoomNum))
				(Print 37 7)
			else
				(Print 37 8)
			)
		)
	)
)
