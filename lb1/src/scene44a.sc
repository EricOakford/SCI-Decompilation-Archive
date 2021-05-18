;;; Sierra Script 1.0 - (do not remove this comment)
(script# 330)
(include game.sh)
(use Main)
(use Intrface)
(use Sound)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	scene44a 0
)

(local
	theCycles
	mouthCued
	saveBits
)
(procedure (Measure &tmp [str 500])
	(GetFarText &rest @str)
	(= theCycles (+ (/ (StrLen @str) 2) 1))
)

(procedure (LilPrint)
	(Measure &rest)
	(LilMouth setScript: cycleMouth)
	(Print &rest
		#at 20 115
		#font 4
		#width 140
		#mode teJustCenter
		#draw
		#dispose
	)
)

(procedure (EthPrint)
	(Measure &rest)
	(EthMouth setScript: cycleMouth)
	(Print &rest
		#at 160 115
		#font 4
		#width 140
		#mode teJustCenter
		#draw
		#dispose
	)
)

(instance scene44a of Room
	(properties
		picture 62
		style IRISOUT
	)
	
	(method (init)
		(super init:)
		(Load FONT 4)
		(HandsOff)
		(myMusic number: 27 loop: -1 play:)
		(Lillian setPri: 1 init:)
		(LilMouth setPri: 2 init: hide:)
		(LilEyes
			setLoop: (Random 2 4)
			setPri: 2
			init:
			stopUpd:
			setScript: LillsEyes
		)
		(Ethel setPri: 1 init:)
		(EthMouth setPri: 2 init: hide:)
		(EthArm
			setLoop: 2
			setCel: 0
			setPri: 3
			ignoreActors: TRUE
			init:
		)
		(if (not (& global173 $0080))
			(self setScript: speech44a)
		else
			(EthArm setScript: TakeASip)
			(LilEyes setScript: LillsEyes)
		)
	)
	
	(method (doit)
		(super doit:)
	)
	
	(method (dispose)
		(if (and (not (& global173 $0080)) global125)
			(|= global173 $0080)
		)
		(super dispose:)
	)
	
	(method (handleEvent event)
		(super handleEvent: event)
	)
)

(instance LillsEyes of Script

	(method (changeState newState)
		(switch (= state newState)
			(0
				(LilEyes setLoop: (+ 4 (* (Random 0 2) 2)) stopUpd:)
				(= seconds (Random 2 5))
			)
			(1
				(LilEyes startUpd: setCycle: BegLoop self)
				(= state -1)
			)
		)
	)
)

(instance speech44a of Script

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
						(Display 330 0
							p_at 48 8
							p_width 256
							p_color vWHITE
							p_back -1
							p_font SYSFONT
							p_save
						)
					)
					(EthPrint 330 1)
					(= seconds 7)
				)
				(2
					(LilPrint 330 2)
					(EthArm setScript: TakeASip)
					(= seconds 5)
				)
				(3
					(if (and (EthArm script?) (< (TakeASip state?) 3))
						(-- state)
						(= cycles 1)
					else
						(EthPrint 330 3)
						(= seconds 7)
					)
				)
				(4
					(LilPrint 330 4)
					(EthArm setScript: TakeASip)
					(= seconds 5)
				)
				(5
					(if (and (EthArm script?) (< (TakeASip state?) 3))
						(-- state)
						(= cycles 1)
					else
						(EthPrint 330 5)
						(= seconds 7)
					)
				)
				(6
					(EthArm setScript: TakeASip)
					(cls)
					(= seconds 7)
				)
				(7
					(if (and (EthArm script?) (< (TakeASip state?) 3))
						(-- state)
						(= cycles 1)
					else
						(curRoom newRoom: prevRoomNum)
					)
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

(instance TakeASip of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if (& global173 $0080)
					(Print 330 6 #dispose)
				)
				(EthArm moveSpeed: 1 setMotion: MoveTo 191 113 self)
			)
			(1
				(EthArm setCycle: EndLoop self)
			)
			(2
				(EthMouth cel: 0 setCycle: Forward show:)
				(= seconds 2)
			)
			(3
				(EthArm setCycle: BegLoop self)
				(EthMouth cel: 0 setCycle: 0 hide:)
			)
			(4
				(EthArm setMotion: MoveTo 165 136 self)
			)
			(5
				(client setScript: 0)
				(if (& global173 $0080)
					(curRoom newRoom: prevRoomNum)
				)
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

(instance Lillian of Prop
	(properties
		y 110
		x 96
		view 517
	)
)

(instance Ethel of Prop
	(properties
		y 110
		x 233
		view 324
	)
)

(instance LilMouth of Prop
	(properties
		y 88
		x 99
		view 517
		loop 2
		cycleSpeed 1
	)
)

(instance LilEyes of Prop
	(properties
		y 75
		x 98
		view 517
		loop 4
		cycleSpeed 1
	)
)

(instance EthMouth of Prop
	(properties
		y 94
		x 212
		view 324
		loop 1
		cycleSpeed 1
	)
)

(instance EthArm of Actor
	(properties
		y 136
		x 165
		yStep 5
		view 324
		illegalBits $0000
	)
)

(instance myMusic of Sound)
