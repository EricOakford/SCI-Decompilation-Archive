;;; Sierra Script 1.0 - (do not remove this comment)
(script# 305)
(include sci.sh)
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
	local0
	theCycles
	local2
)
(procedure (localproc_000c &tmp [temp0 500])
	(GetFarText &rest @temp0)
	(= theCycles (+ (/ (StrLen @temp0) 3) 1))
)

(procedure (localproc_002c)
	(localproc_000c &rest)
	(= theCycles (+ theCycles (/ theCycles 4)))
	(wilbMouth setScript: cycleMouth)
	(Print
		&rest
		#at
		140
		115
		#font
		4
		#width
		140
		#mode
		1
		#draw
		#dispose
	)
)

(procedure (localproc_0068)
	(localproc_000c &rest)
	(= theCycles (+ theCycles (/ theCycles 4)))
	(clarMouth setScript: cycleMouth)
	(Print
		&rest
		#at
		10
		115
		#font
		4
		#width
		140
		#mode
		1
		#draw
		#dispose
	)
)

(instance scene34b of Rm
	(properties
		picture 62
		style $0007
	)
	
	(method (init)
		(super init:)
		(Bset 16)
		(Bset 17)
		(Bset 18)
		(Bset 19)
		(Load rsFONT 4)
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
	(properties)
	
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
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(client setCycle: Beg)
				(= state -1)
				(= seconds (Random 4 6))
			)
		)
	)
)

(instance speech of Script
	(properties)
	
	(method (doit)
		(super doit:)
		(if (and (== (mod state 2) 1) (!= state 9))
			(if (and (== (Hand x?) 122) (== (Hand y?) 135))
				(Smoke show: setCycle: End)
			)
			(Hand setMotion: MoveTo 122 135)
		else
			(Hand setMotion: MoveTo 140 190)
		)
	)
	
	(method (changeState newState)
		(if (cycleMouth client?)
			(= local2 1)
			(= cycles 1)
		else
			(switch (= state newState)
				(0 (= cycles 7))
				(1
					(= local0
						(Display
							305
							0
							dsCOORD
							48
							8
							dsWIDTH
							256
							dsCOLOR
							15
							dsBACKGROUND
							-1
							dsFONT
							0
							dsSAVEPIXELS
						)
					)
					(localproc_002c 305 1)
					(= seconds 10)
				)
				(2
					(localproc_0068 305 2)
					(= seconds 4)
				)
				(3
					(localproc_002c 305 3)
					(= seconds 7)
				)
				(4
					(localproc_0068 305 4)
					(= seconds 10)
				)
				(5
					(localproc_002c 305 5)
					(= seconds 8)
				)
				(6
					(localproc_0068 305 6)
					(= seconds 10)
				)
				(7
					(localproc_002c 305 7)
					(= seconds 8)
				)
				(8
					(localproc_0068 305 8)
					(= seconds 10)
				)
				(9
					(localproc_0068 305 9)
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
				(== evKEYBOARD (event type?))
				(or
					(== (event message?) KEY_S)
					(== (event message?) KEY_s)
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
		(if local2 (= local2 0) (= cycles 1))
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(client cel: 0 setCycle: Fwd show:)
				(= cycles theCycles)
			)
			(1
				(client setScript: 0 hide:)
				(self client: 0)
			)
		)
	)
)

(instance Wilbur of Act
	(properties
		y 106
		x 223
		view 431
	)
)

(instance Clarence of Act
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

(instance Hand of Act
	(properties
		y 190
		x 140
		view 409
	)
)

(instance myMusic of Sound
	(properties)
)
