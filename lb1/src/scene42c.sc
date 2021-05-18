;;; Sierra Script 1.0 - (do not remove this comment)
(script# 353)
(include game.sh)
(use Main)
(use Sound)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	scene42c 0
)

(local
	local0
)
(procedure (localproc_000c)
	(if (not local0)
		(= local0 1)
		(DrawPic 62 IRISIN)
		(cast eachElementDo: #hide)
		(Colonel view: 302 loop: 0 posn: 105 107 setPri: 1)
	)
)

(procedure (localproc_0044)
	(if local0
		(= local0 0)
		(DrawPic 42)
		(DrawPic 162 IRISOUT FALSE)
		(addToPics doit:)
		(cast eachElementDo: #show)
		(if howFast
			(lamp1 setCycle: Forward init:)
			(lamp2 setPri: 11 setCycle: Forward init:)
			(logs setCycle: Forward init:)
		else
			(logs init: stopUpd:)
			(lamp1 init: stopUpd:)
			(lamp2 setPri: 11 init: stopUpd:)
		)
		(Colonel view: 302 loop: 0 cel: 0 posn: 200 140 show:)
		(Chair posn: 177 140 hide:)
		(stand init:)
	)
)

(instance scene42c of Room
	(properties
		picture 62
		style IRISOUT
	)
	
	(method (init)
		(super init:)
		(HandsOff)
		(= saveDisabled TRUE)
		(Bset 34)
		(Load PICTURE 42 162)
		(Load VIEW 301)
		(Load VIEW 309)
		(Load VIEW 310)
		(= local0 1)
		(addToPics
			add: bed table1 table2 table3 sofa mirror lift gate vase flower cannon
		)
		(stand view: 310 loop: 3 posn: 169 90)
		(Chair
			view: 142
			loop: 1
			cel: 9
			ignoreActors: TRUE
			init:
			hide:
		)
		(Colonel
			view: 302
			ignoreActors: TRUE
			posn: 105 107
			setPri: 1
			loop: 0
			cel: 0
			init:
		)
		(myMusic number: 27 loop: -1 play:)
		(|= global173 $0040)
		(self setScript: stoke)
	)
	
	(method (doit)
		(super doit:)
	)
	
	(method (dispose)
		(super dispose:)
	)
	
	(method (handleEvent event)
		(super handleEvent: event)
	)
	
	(method (newRoom n)
		(= saveDisabled FALSE)
		(super newRoom: n)
	)
)

(instance stoke of Script

	(method (changeState newState)
		(switch (= state newState)
			(0 (= cycles 7))
			(1
				(Display 353 0
					p_at 48 8
					p_width 256
					p_color vWHITE
					p_back -1
					p_font SYSFONT
					p_save
				)
				(Colonel cycleSpeed: 2 setCycle: EndLoop self)
			)
			(2
				(localproc_0044)
				(Colonel
					view: 309
					loop: 0
					cel: 0
					setPri: -1
					cycleSpeed: 6
					setCycle: EndLoop self
				)
				(Chair show:)
			)
			(3
				(Colonel
					view: 301
					setStep: 2 2
					setCycle: Walk
					cycleSpeed: 1
					moveSpeed: 1
					setMotion: MoveTo 233 117 self
				)
			)
			(4
				(Colonel setMotion: MoveTo 181 95 self)
			)
			(5
				(Colonel
					view: 310
					loop: 0
					cel: 0
					cycleSpeed: 0
					setCycle: EndLoop self
				)
				(stand loop: 2)
			)
			(6
				(Colonel loop: 1 setCycle: Forward)
				(= cycles 21)
			)
			(7
				(Colonel
					loop: 0
					cel: (- (NumCels Colonel) 1)
					setCycle: BegLoop self
				)
			)
			(8
				(stand loop: 3)
				(Colonel
					view: 301
					setCycle: Walk
					setStep: 2 2
					setCycle: Walk
					cycleSpeed: 1
					moveSpeed: 1
					setMotion: MoveTo 233 117 self
				)
			)
			(9
				(Colonel setMotion: MoveTo 200 140 self)
			)
			(10
				(Colonel
					view: 309
					loop: 0
					cel: 2
					setPri: -1
					cycleSpeed: 3
					setCycle: BegLoop self
				)
			)
			(11
				(Chair hide:)
				(localproc_000c)
				(Colonel
					cel: (- (NumCels Colonel) 1)
					cycleSpeed: 2
					setCycle: BegLoop
					show:
				)
				(= seconds 3)
			)
			(12
				(curRoom newRoom: prevRoomNum)
			)
		)
	)
	
	(method (handleEvent event)
		(super handleEvent: event)
		(if
			(and
				(not (event claimed?))
				(== keyDown (event type?))
				(or
					(== (event message?) `S)
					(== (event message?) `s)
				)
			)
			(cls)
			(curRoom newRoom: prevRoomNum)
		)
	)
)

(instance bed of PicView
	(properties
		y 167
		x 121
		view 142
		loop 1
		cel 2
	)
)

(instance table1 of PicView
	(properties
		y 167
		x 148
		view 142
		loop 1
		cel 3
		priority 15
	)
)

(instance table2 of PicView
	(properties
		y 167
		x 69
		view 142
		loop 1
		cel 3
		priority 15
	)
)

(instance sofa of PicView
	(properties
		y 125
		x 158
		view 142
		loop 1
		priority 8
	)
)

(instance mirror of PicView
	(properties
		y 141
		x 41
		view 142
		loop 1
		cel 1
	)
)

(instance table3 of PicView
	(properties
		y 123
		x 202
		view 142
		loop 1
		cel 4
		priority 8
	)
)

(instance cannon of PicView
	(properties
		y 52
		x 190
		view 142
		loop 1
		cel 5
	)
)

(instance lift of PicView
	(properties
		y 126
		x 296
		view 242
	)
)

(instance gate of PicView
	(properties
		y 126
		x 283
		view 242
		loop 2
	)
)

(instance vase of PicView
	(properties
		y 52
		x 210
		view 142
		loop 1
		cel 7
	)
)

(instance flower of PicView
	(properties
		y 52
		x 170
		view 142
		loop 1
		cel 8
	)
)

(instance lamp1 of Prop
	(properties
		y 44
		x 80
		view 142
	)
)

(instance lamp2 of Prop
	(properties
		y 76
		x 15
		view 142
		cel 1
	)
)

(instance logs of Prop
	(properties
		y 86
		x 189
		view 142
		loop 2
		priority 5
		cycleSpeed 1
	)
)

(instance Colonel of Actor)

(instance Chair of Actor)

(instance stand of Prop)

(instance myMusic of Sound)
