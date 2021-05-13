;;; Sierra Script 1.0 - (do not remove this comment)
(script# 305)
(include game.sh)
(use Main)
(use Intrface)
(use Sound)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	scene34b 0
)

(local
	saveBits
	talkCycles
	mouthCued
)
(procedure (Measure &tmp [str 500])
	(GetFarText &rest @str)
	(= talkCycles (+ (/ (StrLen @str) 3) 1))
)

(procedure (WilbPrint)
	(Measure &rest)
	(= talkCycles (+ talkCycles (/ talkCycles 4)))
	(wilbMouth setScript: cycleMouth)
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
	(Measure &rest)
	(= talkCycles (+ talkCycles (/ talkCycles 4)))
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

(instance scene34b of Room
	(properties
		picture 62
		style IRISOUT
	)
	
	(method (init)
		(super init:)
		(Bset 16)
		(Bset fExaminedMagazine)
		(Bset 18)
		(Bset 19)
		(Load FONT 4)
		(HandsOff)
		(myMusic number: 27 loop: -1 play:)
		(= global154 4)
		(Clarence setPri: 1 init:)
		(clarMouth setPri: 2 init:)
		(clarEye setPri: 2 init: setScript: eyeball2)
		(Hand setLoop: 0 setCel: 1 setPri: 1 yStep: 5 init:)
		(Smoke setPri: 2 init: hide:)
		(Wilbur setLoop: 0 setCel: 0 setPri: 1 yStep: 5 init:)
		(wilbMouth setPri: 2 init:)
		(wilbEye setPri: 2 init: setScript: eyeball)
		(self setScript: speech)
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

(instance eyeball of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(wilbEye loop: (Random 2 4) cel: 1)
				(= seconds (Random 2 6))
			)
			(1
				(wilbEye loop: (Random 2 4) cel: 0)
				(= state -1)
				(= seconds (Random 2 6))
			)
		)
	)
)

(instance eyeball2 of Script

	(method (changeState newState)
		(switch (= state newState)
			(0
				(client setCycle: BegLoop)
				(= state -1)
				(= seconds (Random 4 6))
			)
		)
	)
)

(instance speech of Script

	(method (doit)
		(super doit:)
		(if (and (== (mod state 2) 1) (!= state 9))
			(if (and (== (Hand x?) 122) (== (Hand y?) 135))
				(Smoke show: setCycle: EndLoop)
			)
			(Hand setMotion: MoveTo 122 135)
		else
			(Hand setMotion: MoveTo 140 190)
		)
	)
	
	(method (changeState newState)
		(if (cycleMouth client?)
			(= mouthCued TRUE)
			(= cycles 1)
		else
			(switch (= state newState)
				(0
					(= cycles 7)
				)
				(1
					(= saveBits
						(Display 305 0
							p_at 48 8
							p_width 256
							p_color vWHITE
							p_back -1
							p_font SYSFONT
							p_save
						)
					)
					(WilbPrint 305 1)
					(= seconds 10)
				)
				(2
					(ClarPrint 305 2)
					(= seconds 4)
				)
				(3
					(WilbPrint 305 3)
					(= seconds 7)
				)
				(4
					(ClarPrint 305 4)
					(= seconds 10)
				)
				(5
					(WilbPrint 305 5)
					(= seconds 8)
				)
				(6
					(ClarPrint 305 6)
					(= seconds 10)
				)
				(7
					(WilbPrint 305 7)
					(= seconds 8)
				)
				(8
					(ClarPrint 305 8)
					(= seconds 10)
				)
				(9
					(ClarPrint 305 9)
					(= seconds 10)
				)
				(10
					(cls)
					(clarMouth hide:)
					(wilbEye hide:)
					(clarEye hide:)
					(wilbMouth hide:)
					(Clarence
						setLoop: 7
						setCycle: Walk
						setStep: 5 5
						setMotion: MoveTo -40 (Clarence y?) self
					)
				)
				(11
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

(instance cycleMouth of Script
	(properties)
	
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
				(= cycles talkCycles)
			)
			(1
				(client setScript: 0 hide:)
				(self client: 0)
			)
		)
	)
)

(instance Wilbur of Actor
	(properties
		y 106
		x 223
		view 431
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

(instance clarEye of Prop
	(properties
		y 72
		x 119
		view 409
		loop 8
	)
)

(instance wilbEye of Prop
	(properties
		y 74
		x 221
		view 431
		loop 4
	)
)

(instance wilbMouth of Prop
	(properties
		y 94
		x 220
		view 431
		loop 1
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

(instance myMusic of Sound)
