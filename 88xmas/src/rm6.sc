;;; Sierra Script 1.0 - (do not remove this comment)
(script# rFrosty)
(include game.sh)
(use Main)
(use Sound)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	rm6 0
)

(local
	aKid
	aFrosty
	aHat
	aKidHead
	frostyDances
)
(instance rm6 of Room
	(properties
		picture rFrosty
		style IRISOUT
	)
	
	(method (init)
		(Load VIEW rFrosty)
		((View new:)
			view: rFrosty
			loop: 5
			cel: 0
			posn: 155 156
			ignoreActors:
			stopUpd:
			addToPic:
		)
		((= aKid (Prop new:))
			view: rFrosty
			setLoop: 3
			cel: 1
			setPri: 15
			posn: 187 156
			ignoreActors:
			init:
		)
		((= aKidHead (Prop new:))
			view: rFrosty
			setLoop: 6
			ignoreActors:
			setPri: 15
			posn: 188 116
			init:
			hide:
		)
		((= aFrosty (Actor new:))
			view: rFrosty
			loop: 0
			setCel: 0
			posn: 186 156
			setPri: 9
			init:
			stopUpd:
		)
		((= aHat (Actor new:))
			view: rFrosty
			setLoop: 5
			setCel: 1
			posn: 187 105
			setPri: 14
			init:
			hide:
		)
		(= frostyDances FALSE)
		(super init:)
		(rm6 setScript: MusicScript)
		(self setScript: rm6Script)
	)
)

(instance MusicScript of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				((Sound new:) number: sFrosty loop: 1 play: self)
			)
			(1
				(curRoom newRoom: rManger)
			)
		)
	)
)

(instance rm6Script of Script

	(method (doit)
		(if frostyDances
			(cond 
				((> (aFrosty y:) 199)
					(aKidHead setCel: 1 show:)
				)
				((< (aFrosty y:) 175)
					(aKidHead setCel: 0 show:)
				)
				(else
					(aKidHead hide:)
				)
			)
		)
		(super doit:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= seconds 2)
			)
			(1
				(aKid cycleSpeed: 4 setCycle: BegLoop)
				(= seconds 2)
			)
			(2
				(aKid cycleSpeed: 4 setCycle: EndLoop self)
			)
			(3
				(aKid setLoop: 4 cycleSpeed: 2 setCycle: Forward)
				(= seconds 3)
			)
			(4
				(aFrosty startUpd: setCel: 1 stopUpd:)
				(= seconds 2)
			)
			(5
				(aKid setLoop: 3 cycleSpeed: 4 setCel: 1)
				(= seconds 2)
			)
			(6
				(aKid setCycle: BegLoop)
				(= seconds 1)
			)
			(7
				(aKid cycleSpeed: 4 setCycle: EndLoop self)
			)
			(8
				(aKid setLoop: 4 cycleSpeed: 2 setCycle: Forward)
				(= seconds 2)
			)
			(9
				(aFrosty startUpd: setCel: 2 stopUpd:)
				(= seconds 2)
			)
			(10
				(aKid setLoop: 3 cycleSpeed: 4 setCel: 1)
				(= seconds 2)
			)
			(11
				(aKid setCycle: BegLoop)
				(= seconds 1)
			)
			(12
				(aKid cycleSpeed: 4 setCycle: EndLoop self)
			)
			(13
				(aKid setLoop: 4 cycleSpeed: 2 setCycle: Forward)
				(= seconds 2)
			)
			(14
				(aFrosty startUpd: setCel: 3 stopUpd:)
				(= seconds 2)
			)
			(15
				(aKid setLoop: 3 cycleSpeed: 4 setCel: 1)
				(= seconds 2)
			)
			(16
				(aKid setCycle: BegLoop)
				(= seconds 1)
			)
			(17
				(aKid cycleSpeed: 4 setCycle: EndLoop self)
			)
			(18
				(aKid setLoop: 4 cycleSpeed: 2 setCycle: Forward)
				(= seconds 2)
			)
			(19
				(aFrosty startUpd: setCel: 4 stopUpd:)
				(= seconds 2)
			)
			(20
				(aKid setLoop: 3 cycleSpeed: 4 setCel: 1)
				(= seconds 2)
			)
			(21
				(aKid setCycle: BegLoop)
				(= seconds 1)
			)
			(22
				(aKid cycleSpeed: 4 setCycle: EndLoop self)
			)
			(23
				(aKid setLoop: 4 cel: 0)
				(= seconds 1)
			)
			(24
				(aHat
					show:
					moveSpeed: 1
					setMotion: MoveTo 187 97 self
				)
				(aKid setCel: 1 stopUpd:)
			)
			(25
				(aKid startUpd: setLoop: 3 cycleSpeed: 4 setCel: 1)
				(= seconds 3)
			)
			(26
				(aKid startUpd: setCycle: BegLoop)
				(= seconds 1)
			)
			(27
				(aFrosty
					startUpd:
					setLoop: 1
					setCel: 0
					cycleSpeed: 4
					setCycle: Forward
				)
				(aHat dispose:)
				(= seconds 3)
			)
			(28
				(aKid setCycle: EndLoop)
				(= seconds 2)
			)
			(29
				(aKid posn: 187 159 stopUpd:)
				(aFrosty setLoop: 2 moveSpeed: 2 setMotion: MoveTo 202 127)
				(= seconds 4)
			)
			(30
				(= frostyDances TRUE)
				(aFrosty
					moveSpeed: 4
					illegalBits: cLRED
					setMotion: Wander
				)
			)
		)
	)
)
