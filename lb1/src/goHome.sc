;;; Sierra Script 1.0 - (do not remove this comment)
(script# 785)
(include game.sh)
(use Main)
(use Intrface)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	goHome 0
)

(local
	local0
)
(instance goHome of Room
	
	(method (init)
		(super init:)
		(HandsOff)
		(Load FONT 41)
		(LoadMany PICTURE 83 84)
		(Load VIEW 183)
		(Load SOUND 64)
		(self curPic: 79)
		(DrawPic 79 WIPEUP TRUE 1)
		(actor1
			view: 204
			setLoop: 0
			posn: 310 145
			setPri: 13
			setStep: 1 1
			setScript: MainAction
			init:
		)
		(actor1 cel: (- (NumCels actor1) 1))
		(actor2
			view: 204
			loop: 1
			cel: 0
			setPri: 12
			setCycle: Forward
			setStep: 1 1
			setScript: Polling
			init:
		)
		(Display 785 0
			p_at 100 30
			p_width 240
			p_color vWHITE
			p_back -1
			p_font SYSFONT
		)
		(cSound number: 5 loop: -1 play:)
	)
	
	(method (doit)
		(super doit:)
	)
	
	(method (dispose)
		(super dispose:)
	)
	
	(method (handleEvent event)
		(if (event claimed?) (return))
	)
	
	(method (cue)
		(cls)
		(curRoom newRoom: 781)
	)
)

(instance Polling of Script

	(method (changeState newState)
		(switch (= state newState)
			(0
				(if (> local0 1)
					(= state 2)
				)
				(= cycles (Random 15 50))
			)
			(1
				(if (actor1 cel?)
					(actor1 setCycle: BegLoop self)
				else
					(= cycles 1)
				)
			)
			(2
				(actor1 setCycle: EndLoop self)
				(= state -1)
			)
		)
	)
)

(instance MainAction of Script
	
	(method (doit)
		(switch local0
			(1
				(actor2 posn: (- (actor1 x?) 37) (+ (actor1 y?) 1))
			)
			(3
				(actor2 posn: (+ (actor1 x?) 10) (- (actor1 y?) 35))
				(actor3 posn: (- (actor1 x?) 87) (+ (actor1 y?) 2))
			)
		)
		(super doit:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= local0 1)
				(actor1 setMotion: MoveTo 9 145 self)
			)
			(1
				(= local0 2)
				(cSound fade:)
				(goHome curPic: 83)
				(DrawPic 83 IRISOUT TRUE 0)
				(actor1 stopUpd:)
				(actor2
					view: 183
					setLoop: 3
					posn: 134 137
					setPri: 14
					cycleSpeed: 1
					setCycle: EndLoop self
				)
				(Display 785 1
					p_at 90 30
					p_width 240
					p_color vWHITE
					p_back -1
					p_font SYSFONT
				)
			)
			(2
				(cSound number: 64 loop: -1 play:)
				(actor2
					setLoop: 3
					setCel: (- (NumCels actor2) 1)
					setStep: 4 4
					setMotion: MoveTo 278 178 self
				)
			)
			(3
				(= local0 3)
				(actor1
					view: 183
					loop: 0
					posn: -13 121
					setPri: 12
					setStep: 1 1
					setMotion: MoveTo 180 121 self
				)
				(actor2
					view: 183
					setLoop: 2
					setPri: 12
					cycleSpeed: 0
					setCycle: Forward
				)
				(actor3
					view: 183
					setLoop: 1
					setPri: 12
					setCycle: Forward
					init:
				)
			)
			(4
				(= local0 4)
				(goHome curPic: 84)
				(DrawPic 84 IRISOUT TRUE 0)
				(addToPics add: body hand eachElementDo: #init doit:)
				(actor1
					view: 284
					loop: 1
					cel: 0
					posn: 250 74
					cycleSpeed: 1
					startUpd:
					setCycle: EndLoop
					setMotion: 0
				)
				(actor2
					view: 284
					loop: 2
					cel: 0
					posn: 258 82
					priority: 14
					cycleSpeed: 1
					setCycle: 0
				)
				(actor3
					view: 284
					loop: 5
					setPri: 0
					setStep: 2 2
					x: -10
					y: 17
					cycleSpeed: 0
					setMotion: MoveTo 336 17
				)
				(Print 785 2 #at 17 135 #width 280 #dispose)
				(= seconds 8)
			)
			(5 (actor2 setCycle: EndLoop self))
			(6
				(actor2 loop: 3 cel: 0 setCycle: Forward)
				(Print 785 3 #at 17 135 #width 280 #dispose)
				(= seconds 8)
			)
			(7
				(cls)
				(actor2 setCycle: BegLoop self)
			)
			(8
				(actor1 setCycle: BegLoop self)
				(actor2 loop: 2)
				(actor2 cel: (- (NumCels actor2) 1) setCycle: BegLoop)
			)
			(9
				(cSound fade:)
				(Display 785 4
					p_at 111 49
					p_width 50
					p_color vBROWN
					p_back -1
					p_font 41
				)
				(Display 785 4
					p_at 110 47
					p_width 50
					p_color vYELLOW
					p_back -1
					p_font 41
				)
				(= seconds 6)
			)
			(10
				(curRoom newRoom: 786)
			)
		)
	)
)

(instance body of PicView
	(properties
		y 156
		x 235
		view 284
	)
)

(instance hand of PicView
	(properties
		y 105
		x 248
		view 284
		loop 4
		priority 15
	)
)

(instance actor1 of Actor)

(instance actor2 of Actor)

(instance actor3 of Actor)
