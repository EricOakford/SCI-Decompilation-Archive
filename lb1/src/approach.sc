;;; Sierra Script 1.0 - (do not remove this comment)
(script# 782)
(include game.sh)
(use Main)
(use Intrface)
(use Sound)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	approach 0
)

(local
	saveBits
	saveBits2
	theX
	theY
)
(procedure (localproc_020a)
	(= saveBits
		(Display &rest
			105 41
			p_mode teJustCenter
			p_at theX theY
			p_width 300
			p_color vWHITE
			p_save
		)
	)
)

(procedure (localproc_022c)
	(= saveBits2
		(Display &rest
			105 41
			p_mode teJustCenter
			p_at theX theY
			p_width 300
			p_color vBLACK
			p_save
		)
	)
)

(procedure (localproc_024d)
	(Print &rest
		#at 10 94
		#font 4
		#width 145
		#mode teJustLeft
		#dispose
	)
)

(procedure (localproc_026a)
	(Print &rest
		#at 160 94
		#font 4
		#width 142
		#mode teJustLeft
		#dispose
	)
)

(instance Laura of Prop)

(instance Lillian of Prop)

(instance lHead of Prop)

(instance eHead of Prop)

(instance light1 of Prop)

(instance light2 of Prop)

(instance Thunder of Sound)

(instance approach of Room
	(properties
		picture 28
	)
	
	(method (init)
		(super init:)
		(LoadMany FONT 4 41)
		(Load SOUND 18)
		(addToPics add: Sign Bird1 Bird2 doit:)
		(Thunder number: 18 loop: -1 play:)
		(light1 view: 128 loop: 2 cel: 1 posn: 86 42 init:)
		(light2 view: 128 loop: 3 cel: 1 posn: 157 51 init:)
		(Laura view: 128 loop: 4 cel: 0 posn: 148 187 init:)
		(Lillian view: 128 loop: 4 cel: 1 posn: 180 187 init:)
		(lHead
			view: 128
			loop: 5
			cel: 0
			setPri: 15
			posn: 179 147
			init:
		)
		(eHead
			view: 128
			loop: 6
			cel: 0
			setPri: 15
			posn: 148 148
			init:
		)
		(self setScript: openning)
	)
	
	(method (doit)
		(super doit:)
	)
	
	(method (dispose)
		(super dispose:)
	)
	
	(method (handleEvent event)
		(if (event claimed?) (return))
		(switch (event type?)
			(keyDown
				(cond 
					(
						(or
							(== (event message?) `S)
							(== (event message?) `s)
						)
						(event claimed: TRUE)
						(curRoom newRoom: 783)
					)
					(
						(or
							(== (event message?) ENTER)
							(== (event message?) SPACEBAR)
						)
						(Bset fSkippedIntro)
					)
				)
			)
			(mouseDown
				(Bset fSkippedIntro)
			)
		)
		(if (Btst fSkippedIntro)
			(event claimed: TRUE)
			(curRoom newRoom: 44)
		)
	)
)

(instance openning of Script
	
	(method (doit)
		(super doit:)
		(if
			(or
				(and (== (Thunder prevSignal?) 50) (== state 0))
				(and (== (Thunder prevSignal?) 60) (== state 6))
				(and (== (Thunder prevSignal?) -1) (== state 11))
			)
			(Thunder prevSignal: 0)
			(= cycles 1)
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
			)
			(1
				(light1 setCycle: Forward)
				(light2 setCycle: Forward)
				(= cycles 6)
			)
			(2
				(light1 setCycle: EndLoop)
				(light2 setCycle: EndLoop)
				(= seconds 2)
			)
			(3
				(= theX 11)
				(= theY 10)
				(localproc_022c 782 0)
				(= theX 10)
				(= theY 12)
				(localproc_020a 782 0)
				(= seconds 6)
			)
			(4 (lHead setCycle: EndLoop self))
			(5
				(Display 782 1 p_restore saveBits)
				(Display 782 1 p_restore saveBits2)
				(localproc_026a 782 2)
				(= seconds 4)
			)
			(6
				(cls)
				(lHead setCycle: BegLoop)
			)
			(7
				(light1 setCycle: Forward)
				(light2 setCycle: Forward)
				(= cycles 6)
			)
			(8
				(cls)
				(light1 setCycle: EndLoop)
				(light2 setCycle: EndLoop)
				(= seconds 3)
			)
			(9
				(eHead setCycle: EndLoop)
				(localproc_024d 782 3)
				(= seconds 5)
			)
			(10
				(cls)
				(lHead setCycle: EndLoop)
				(localproc_026a 782 4)
				(= seconds 5)
			)
			(11
				(cls)
				(lHead setCycle: BegLoop)
				(eHead setCycle: BegLoop)
				(Thunder fade: self)
			)
			(12
				(if
				(and (== (lHead cel?) 0) (== (eHead cel?) 0))
					(client setScript: 0)
					(curRoom newRoom: 783)
				else
					(= state 11)
					(= cycles 1)
				)
			)
		)
	)
)

(instance Sign of PicView
	(properties
		y 135
		x 145
		view 128
		loop 1
		cel 1
		priority 12
	)
)

(instance Bird1 of PicView
	(properties
		y 103
		x 111
		view 128
		loop 1
		priority 12
	)
)

(instance Bird2 of PicView
	(properties
		y 103
		x 207
		view 128
		loop 1
		priority 12
	)
)
