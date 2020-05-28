;;; Sierra Script 1.0 - (do not remove this comment)
(script# 650)
(include game.sh)
(use Main)
(use Sq4Feature)
(use Motion)
(use Game)
(use System)

(public
	rm650 0
)

(instance rm650 of Room
	(properties
		picture 650
	)
	
	(method (init)
		(Load VIEW 410)
		(pod init:)
		(ego
			init:
			view: 378
			setLoop: 0
			cel: 0
			posn: 106 143
			illegalBits: 0
			setPri: 13
			ignoreActors: TRUE
			normal: 0
			moveHead: 0
		)
		(hatch init: setPri: pYELLOW)
		(super init:)
		(self setScript: meltRoger)
	)
)

(instance meltRoger of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(globalSound number: 124 loop: 1 vol: 127 play: self)
			)
			(1 (= seconds 1))
			(2
				(globalSound number: 142 loop: 1 play:)
				(hatch setCycle: EndLoop self)
			)
			(3
				(globalSound stop:)
				(= seconds (Random 2 4))
			)
			(4
				(ego cel: 0 setCycle: EndLoop self)
			)
			(5
				(NormalEgo (ego loop?) 0)
				(ego setPri: pYELLOW)
				(= cycles 1)
			)
			(6 (ego setHeading: 135 self))
			(7
				(ego
					setLoop: 4
					heading: 135
					setMotion: MoveTo 110 142 self
				)
			)
			(8
				(globalSound play: loop: 1)
				(hatch setCycle: BegLoop self)
			)
			(9
				(hatch stopUpd:)
				(globalSound number: 886 loop: 1 play: setVol: 127)
				(ego view: 410 cel: 0 cycleSpeed: 24 setCycle: EndLoop self)
			)
			(10 (= cycles 4))
			(11 (EgoDead iconMELTED deathORTEGA))
		)
	)
)

(instance hatch of Sq4Prop
	(properties
		x 80
		y 107
		view 410
		loop 2
		priority 5
		signal (| ignrAct ignrHrz fixPriOn)
	)
)

(instance pod of Sq4Prop
	(properties
		x 93
		y 146
		view 410
		loop 1
		signal (| ignrAct ignrHrz)
	)
	
	(method (init)
		(super init:)
		(self setPri: 4 addToPic:)
	)
)
