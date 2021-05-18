;;; Sierra Script 1.0 - (do not remove this comment)
(script# 323)
(include game.sh)
(use Main)
(use Intrface)
(use Sound)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	scene36b 0
)

(local
	local0
	theCycles
	mouthCued
	saveBits
)
(procedure (Measure &tmp [str 500])
	(GetFarText &rest @str)
	(= theCycles (+ (/ (StrLen @str) 3) 1))
)

(procedure (GlorPrint)
	(puff cel: 0 setCycle: EndLoop show:)
	(Measure &rest)
	(= theCycles (+ theCycles (/ theCycles 4)))
	(glorMouth setScript: cycleMouth)
	(Print &rest
		#at 140 115
		#font 4
		#width 140
		#mode teJustCenter
		#draw
		#dispose
	)
)

(procedure (ClarPrint)
	(puff hide:)
	(Measure &rest)
	(= theCycles (+ theCycles (/ theCycles 2)))
	(clarMouth setScript: cycleMouth)
	(Print &rest
		#at 10 115
		#font 4
		#width 140
		#mode teJustCenter
		#draw
		#dispose
	)
)

(procedure (localproc_00bf)
	(|= global173 $0001)
	(= [global368 1] 0)
	(Bset 23)
)

(instance scene36b of Room
	(properties
		picture 62
		style IRISOUT
	)
	
	(method (init)
		(super init:)
		(Load FONT 4)
		(HandsOff)
		(myMusic number: 27 loop: -1 play:)
		(glorSmoke
			setLoop: 4
			setPri: 2
			ignoreActors: TRUE
			init:
			stopUpd:
			hide:
		)
		(glow init: stopUpd: hide:)
		(glorMouth setPri: 2 init:)
		(glorEye setPri: 2 init: stopUpd: setScript: GlorsEyes)
		(Gloria setPri: 1 ignoreActors: 1 init:)
		(puff setPri: 1 init: hide:)
		(glorHand
			setLoop: 1
			setCel: 0
			setPri: 3
			xStep: 5
			yStep: 5
			ignoreActors: TRUE
			init:
		)
		(if (== global154 4)
			(Load FONT 41)
			(LoadMany 143 406)
			(Load VIEW 642)
			(LoadMany SOUND 29 94 95 96)
			(= global154 5)
			(Clarence setPri: 1 init:)
			(clarMouth setPri: 2 init:)
			(clarEye
				setLoop: 8
				setPri: 2
				init:
				stopUpd:
				setScript: ClarsEye
			)
			(Hand setLoop: 0 setCel: 1 setPri: 1 yStep: 5 init:)
			(Smoke setPri: 2 init: hide:)
			(self setScript: speech36b)
		else
			(self setScript: twice)
		)
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
)

(instance ClarsEye of Script

	(method (changeState newState)
		(switch (= state newState)
			(0
				(clarEye cel: (^ (clarEye cel?) 1) forceUpd:)
				(= state -1)
				(if (clarEye cel?)
					(= cycles 2)
				else
					(= seconds (Random 2 5))
				)
			)
		)
	)
)

(instance GlorsEyes of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(glorEye stopUpd:)
				(= seconds (Random 2 5))
			)
			(1
				(glorEye startUpd: setCycle: BegLoop self)
				(= state -1)
			)
		)
	)
)

(instance speech36b of Script
	(properties)
	
	(method (doit)
		(super doit:)
		(if (>= state 1)
			(if (and (== (mod state 2) 0) (!= state 8))
				(= local0 0)
				(glorMouth loop: 3 cycleSpeed: 0 setCycle: Forward)
				(glorHand setMotion: MoveTo 167 111)
				(glow hide:)
				(if (and (== (glorHand x?) 167) (== (glorHand y?) 111))
					(glorSmoke posn: 148 89 show: setCycle: Forward)
				)
				(clarMouth stopUpd:)
				(if (and (== (Hand x?) 122) (== (Hand y?) 135))
					(Smoke show: setCycle: EndLoop)
				)
				(Hand setMotion: MoveTo 122 135)
			else
				(clarMouth setCycle: Forward)
				(Hand setMotion: MoveTo 140 190)
				(cond 
					(
						(and
							(== (glorHand x?) 186)
							(== (glorHand y?) 111)
							(== local0 0)
						)
						(= local0 1)
						(glow show:)
						(glorMouth loop: 2 setCycle: Forward cycleSpeed: 3)
						(glorSmoke hide:)
					)
					((not local0)
						(glorHand setMotion: MoveTo 186 111)
						(glorSmoke setMotion: MoveTo 169 89)
						(glorMouth setCycle: EndLoop)
					)
				)
			)
		)
	)
	
	(method (changeState newState)
		(if (cycleMouth client?)
			(= mouthCued TRUE)
			(= cycles 1)
		else
			(switch (= state newState)
				(0
					(cond 
						((not global216)
							(= state -1)
						)
						((not (& global118 $0002))
							(|= global118 $0002)
							(self setScript: (ScriptID 406 0))
							(= state -1)
						)
						((self script?)
							(= state -1)
						)
					)
					(= cycles 1)
				)
				(1
					(= saveBits
						(Display 323 0
							p_at 48 8
							p_width 256
							p_color vWHITE
							p_back -1
							p_font SYSFONT
							p_save
						)
					)
					(ClarPrint 323 1)
					(= seconds 10)
				)
				(2
					(GlorPrint 323 2)
					(= seconds 4)
				)
				(3
					(ClarPrint 323 3)
					(= seconds 7)
				)
				(4
					(GlorPrint 323 4)
					(= seconds 10)
				)
				(5
					(ClarPrint 323 5)
					(= seconds 8)
				)
				(6
					(GlorPrint 323 6)
					(= seconds 10)
				)
				(7
					(ClarPrint 323 7)
					(= seconds 8)
				)
				(8
					(ClarPrint 323 8)
					(= seconds 8)
				)
				(9
					(cls)
					(clarMouth hide:)
					(clarEye hide:)
					(glorMouth hide:)
					(Clarence
						setLoop: 7
						setStep: 5 5
						setMotion: MoveTo -40 (Clarence y?) self
					)
				)
				(10
					(curRoom newRoom: prevRoomNum)
				)
			)
		)
	)
	
	(method (handleEvent event)
		(super handleEvent: event)
		(if
			(and
				(not (event claimed?))
				(not script)
				(== keyDown (event type?))
				(or
					(== (event message?) `S)
					(== (event message?) `s)
				)
			)
			(cls)
			(if (not (& global173 $0001))
				(localproc_00bf)
			)
			(curRoom newRoom: prevRoomNum)
		)
	)
)

(instance twice of Script

	(method (changeState newState)
		(switch (= state newState)
			(0
				(glorSmoke posn: 148 89 show: setCycle: Forward)
				(= cycles 1)
			)
			(1
				(Print 323 9 #dispose)
				(= seconds 4)
			)
			(2
				(cls)
				(glorSmoke setMotion: MoveTo 169 89)
				(glorHand setMotion: MoveTo 186 111 self)
			)
			(3
				(curRoom newRoom: prevRoomNum)
			)
		)
	)
)

(instance cycleMouth of Script

	(method (doit)
		(super doit:)
		(if mouthCued
			(= mouthCued FALSE)
			(= cycles 1)
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(client cel: 0 setCycle: Forward show:)
				(= cycles theCycles)
			)
			(1
				(client setScript: 0 hide:)
				(self client: 0)
			)
		)
	)
)

(instance Clarence of Actor
	(properties
		y 115
		x 102
		view 409
	)
)

(instance clarMouth of Prop
	(properties
		y 94
		x 114
		view 409
		loop 2
	)
)

(instance Smoke of Prop
	(properties
		y 82
		x 116
		view 409
		loop 4
	)
)

(instance Hand of Actor
	(properties
		y 190
		x 140
		view 409
	)
)

(instance Gloria of Prop
	(properties
		y 110
		x 231
		view 367
	)
)

(instance glorMouth of Prop
	(properties
		y 96
		x 211
		view 367
		loop 3
	)
)

(instance clarEye of Prop
	(properties
		y 72
		x 118
		view 409
	)
)

(instance glorSmoke of Actor
	(properties
		y 89
		x 148
		yStep 5
		view 367
		xStep 5
	)
)

(instance glorEye of Prop
	(properties
		y 76
		x 204
		view 367
		loop 7
	)
)

(instance glow of Prop
	(properties
		y 89
		x 170
		view 367
		loop 1
		cel 1
	)
)

(instance puff of Prop
	(properties
		y 88
		x 196
		view 367
		loop 8
		signal ignrAct
	)
)

(instance glorHand of Actor
	(properties
		y 111
		x 167
		view 367
	)
)

(instance myMusic of Sound)
