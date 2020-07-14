;;; Sierra Script 1.0 - (do not remove this comment)
(script# 126)
(include game.sh)
(use Main)
(use LoadMany)
(use Sound)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	demo7 0
)

(local
	mD1
	mD2
)
(procedure (JimSays theString &tmp [temp0 3])
	(if mD1
		(Display 126 0 p_restore mD1)
		(Display 126 0 p_restore mD2)
		(= mD1 0)
	)
	(if theString
		(= mD2
			(Display theString
				p_width 300
				p_at 10 5
				p_mode teJustLeft
				p_font 11
				p_color 0
				p_save
			)
		)
		(= mD1
			(Display theString
				p_width 300
				p_at 10 5
				p_mode teJustLeft
				p_font 9
				p_color 7
				p_save
			)
		)
	)
)

(instance fakeEgo of Actor
	(properties)
)

(instance demo7 of Room
	(properties
		picture 56
		style FADEOUT
	)
	
	(method (init)
		(super init:)
		(HandsOff)
		(LoadMany FONT 9 11)
		(Load VIEW 592)
		(fakeEgo
			view: 1003
			loop: 0
			setStep: 3 2
			posn: 88 83
			illegalBits: -32768
		)
		(addToPics add: fireplace doit:)
		(dust init:)
		(tv setCycle: Forward init:)
		(self setScript: demoScript)
		(LoadMany SOUND 937 940 566)
		(sfx3 number: 565 loop: -1 vol: 20 play: fade: 127 5 10 0)
	)
	
	(method (doit &tmp temp0)
		(if script (script doit:))
	)
	
	(method (handleEvent event)
		(cond 
			((event claimed?) (return))
			(script (return))
			(else (event claimed: TRUE))
		)
	)
)

(instance demoScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(JimSays
					{With the most evil and depraved opponents he's ever faced - a networked conspiracy of drugs and ritual murder.}
				)
				(dust setCycle: EndLoop self)
			)
			(1
				(fakeEgo setCycle: CycleTo 3 1 self init:)
				(music fade: 0 5 10 1)
				(dust dispose:)
			)
			(2
				(fakeEgo setPri: 6 cycleSpeed: 10 setCycle: EndLoop self)
			)
			(3
				(JimSays
					{Because this time, the difference between catching the bad guys and letting them slip through your grasp...}
				)
				(= seconds 2)
			)
			(4
				(fakeEgo
					posn: 67 86
					setPri: -1
					cycleSpeed: 8
					cel: 0
					loop: 1
					setCycle: EndLoop self
				)
			)
			(5 (= seconds 1))
			(6
				(fakeEgo
					cycleSpeed: 6
					cel: 0
					setLoop: 2
					setCycle: Walk
					setMotion: MoveTo 95 96 self
				)
				(music fade:)
			)
			(7
				(LoadMany SOUND 566 940)
				(fakeEgo setMotion: MoveTo 140 98 self)
				(badGuy cycleSpeed: 9 setCycle: CycleTo 5 1 self init:)
			)
			(8
				(sfx1 number: 566 loop: 1 play:)
				(badGuy cycleSpeed: 9 setCycle: EndLoop self init:)
			)
			(9)
			(10
				(JimSays 0)
				(badGuy
					loop: 1
					cel: 0
					posn: 104 148
					cycleSpeed: 8
					setCycle: CycleTo 5 1 self
				)
				(sfx1 number: 940 play:)
			)
			(11
				(bulletHole init:)
				(badGuy setCycle: EndLoop self)
				(fakeEgo loop: 3 cel: 0)
			)
			(12
				(fakeEgo cel: 0 setCycle: CycleTo 5 1 self)
				(sfx3 fade:)
			)
			(13
				(sfx2 number: 937 loop: 1 play:)
				(fakeEgo setCycle: CycleTo 7 1)
				(badGuy loop: 3 cel: 0 posn: 89 157 setCycle: EndLoop self)
			)
			(14 (= seconds 3))
			(15 (curRoom newRoom: 122))
		)
	)
)

(instance badGuy of Prop
	(properties
		x 86
		y 163
		view 592
	)
)

(instance fireplace of PicView
	(properties
		x 203
		y 104
		view 595
	)
)

(instance tv of Prop
	(properties
		x 237
		y 127
		view 595
		loop 1
		signal ignrAct
	)
)

(instance dust of Prop
	(properties
		x 106
		y 68
		view 598
		signal ignrAct
		cycleSpeed 10
	)
)

(instance bulletHole of View
	(properties
		x 165
		y 70
		view 592
		loop 4
	)
)

(instance sfx1 of Sound
	(properties
		priority 14
	)
)

(instance sfx2 of Sound
	(properties
		priority 15
	)
)

(instance sfx3 of Sound
	(properties
		priority 13
	)
)
