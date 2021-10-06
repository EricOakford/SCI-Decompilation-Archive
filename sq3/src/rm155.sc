;;; Sierra Script 1.0 - (do not remove this comment)
(script# 155)
(include game.sh)
(use Main)
(use Sound)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	rm155 0
)

(local
	saveBits
	local1
)
(instance rm155 of Room
	(properties
		picture 155
		horizon 1
	)
	
	(method (init &tmp [temp0 50])
		style 16
		(Load PICTURE 156)
		(Load PICTURE 777)
		(Load VIEW 8)
		(Load VIEW 9)
		(Load SOUND 100)
		(Load SOUND 64)
		(Load SOUND 65)
		(Load SOUND 66)
		(super init:)
		(self setScript: rmScript)
	)
	
	(method (handleEvent event)
		(if (event claimed?) (return))
		(if (event type?)
			(= oldCursor normalCursor)
			(theGame setCursor: normalCursor (HaveMouse))
			(Display 155 0 p_restore saveBits)
			(eyes dispose:)
			(bub0 dispose:)
			(bub1 dispose:)
			(bub2 dispose:)
			(bub3 dispose:)
			(theMusic stop:)
			(curRoom drawPic: 777)
			(RedrawCast)
			(curRoom newRoom: 2)
		)
	)
)

(instance rmScript of Script
	(method (doit)
		(if (== (theMusic prevSignal?) -1)
			(if
				(and
					(== (theMusic number?) 64)
					(!= (theMusic state?) 3)
					(== (self state?) 1)
				)
				(self cue:)
			)
			(if (and (== (theMusic number?) 66) (!= (theMusic state?) 3))
				(if (< (self state?) 19) (theMusic play:))
				(if (== (self state?) 19) (self cue:))
			)
		)
		(super doit:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theMusic number: 64 loop: 1 play:)
				(eyes init:)
				(= seconds 4)
			)
			(1
				(ShakeScreen 3)
				(= saveBits
					(Display 155 1
						p_width 250
						p_at 36 100
						p_mode teJustCenter
						p_font 300
						p_color vYELLOW
						p_save
					)
				)
			)
			(2
				(Display 155 0 p_restore saveBits)
				(curRoom drawPic: 156 3)
				(doorSound play:)
				(= seconds 4)
			)
			(3
				(doorSound stop:)
				(theMusic number: 66 loop: 1 play:)
				(eyes setCycle: CycleTo 3 1 self)
			)
			(4
				(= seconds 3)
			)
			(5
				(eyes cel: 0 setCycle: CycleTo 3 1 self)
			)
			(6
				(= cycles 15)
			)
			(7
				(eyes cel: 0)
				(= cycles 2)
			)
			(8
				(eyes setCycle: EndLoop self)
			)
			(9
				(bub0 init:)
			)
			(10
				(eyes
					setLoop: 1
					setCel: 0
					cycleSpeed: 2
					setCycle: EndLoop self
				)
			)
			(11
				(eyes setCycle: BegLoop self)
			)
			(12
				(eyes setCycle: EndLoop self)
			)
			(13
				(eyes setCycle: CycleTo 2 -1 self)
			)
			(14
				(eyes setCel: 2 stopUpd:)
				(= seconds 3)
			)
			(15
				(eyes
					setLoop: 0
					setCel: 0
					cycleSpeed: 0
					setCycle: EndLoop self
				)
			)
			(16
				(eyes setCycle: BegLoop self)
			)
			(17
				(eyes setCycle: EndLoop self)
			)
			(18
				(eyes stopUpd:)
				(= cycles 2)
			)
			(19
				(eyes stopUpd:)
			)
			(20
				(= oldCursor normalCursor)
				(theGame setCursor: normalCursor (HaveMouse))
				(theMusic stop:)
				(eyes dispose:)
				(curRoom drawPic: 777)
				(RedrawCast)
				(curRoom newRoom: 2)
			)
		)
	)
)

(instance bubScript of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(bub1 init: stopUpd:)
				(= cycles 3)
			)
			(1
				(bub2 init: stopUpd:)
				(= cycles 3)
			)
			(2
				(whereSound play:)
				(bub3 init: stopUpd:)
				(= seconds 5)
			)
			(3
				(rmScript cue:)
				(bub0 dispose:)
				(bub1 dispose:)
				(bub2 dispose:)
				(bub3 dispose:)
			)
		)
	)
)

(instance bub0 of Prop
	(method (init)
		(super init:)
		(self
			view: 9
			setCel: 0
			ignoreActors: TRUE
			posn: 184 51
			setPri: 15
			setScript: bubScript
		)
	)
)

(instance bub1 of Prop
	(method (init)
		(super init:)
		(self
			view: 9
			setCel: 1
			ignoreActors: TRUE
			posn: 197 47
			setPri: 15
		)
	)
)

(instance bub2 of Prop
	(method (init)
		(super init:)
		(self
			view: 9
			setCel: 2
			ignoreActors: TRUE
			posn: 215 42
			setPri: 15
		)
	)
)

(instance bub3 of Prop
	(method (init)
		(super init:)
		(self
			view: 9
			setCel: 3
			ignoreActors: TRUE
			posn: 243 34
			setPri: 15
		)
	)
)

(instance eyes of Actor
	(properties
		y 88
		x 145
		view 8
	)
	
	(method (init)
		(super init:)
		(self
			loop: 0
			cel: 0
			ignoreActors: TRUE
			setPri: 9
			cycleSpeed: 4
			illegalBits: 0
		)
	)
)

(instance whereSound of Sound
	(properties
		number 100
		priority 5
	)
)

(instance doorSound of Sound
	(properties
		number 65
		priority 5
	)
)
