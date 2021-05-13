;;; Sierra Script 1.0 - (do not remove this comment)
(script# 302)
(include game.sh)
(use Main)
(use Intrface)
(use Sound)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	scene38b 0
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

(procedure (EthelPrint)
	(Measure &rest)
	(= talkCycles (+ talkCycles (/ talkCycles 4)))
	(Mouth setScript: cycleMouth)
	(ParrotMouth setCycle: 0)
	(Print &rest
		#at 160 120
		#font 4
		#width 140
		#mode teJustCenter
		#dispose
	)
)

(procedure (ParrotPrint)
	(Measure &rest)
	(ParrotMouth setScript: cycleMouth)
	(Mouth setCycle: 0)
	(Print &rest
		#at 20 120
		#font 4
		#width 140
		#mode teJustCenter
		#dispose
	)
)

(instance scene38b of Room
	(properties
		picture 62
		style IRISOUT
	)
	
	(method (init)
		(super init:)
		(Load FONT 4)
		(HandsOff)
		(addToPics add: parrotBody doit:)
		(Ethel setPri: 1 init:)
		(Mouth setPri: 2 init:)
		(Eye setPri: 2 init: setScript: ethelEyes)
		(Arm setLoop: 8 setPri: 2 setCycle: 0 init: hide:)
		(ParrotMouth setPri: 2 init:)
		(myMusic number: 27 loop: -1 play:)
		(if (not (& global173 $0002))
			(self setScript: speech38)
		else
			(self setScript: Salute)
		)
	)
	
	(method (doit)
		(super doit:)
	)
	
	(method (dispose)
		(super dispose:)
		(|= global173 $0002)
	)
	
	(method (handleEvent event)
		(super handleEvent: event)
	)
)

(instance speech38 of Script
	
	(method (changeState newState)
		(if (cycleMouth client?)
			(= mouthCued TRUE)
			(= cycles 1)
		else
			(switch (= state newState)
				(0
					(= saveBits
						(Display 302 0
							p_at 48 8
							p_width 256
							p_color vWHITE
							p_back -1
							p_font SYSFONT
							p_save
						)
					)
					(EthelPrint 302 1)
					(= seconds 5)
				)
				(1
					(cls)
					(ParrotPrint 302 2)
					(= seconds 5)
				)
				(2
					(cls)
					(EthelPrint 302 3)
					(= seconds 8)
				)
				(3
					(cls)
					(EthelPrint 302 4)
					(= seconds 6)
				)
				(4
					(cls)
					(ParrotPrint 302 5)
					(= seconds 5)
				)
				(5
					(cls)
					(Arm setScript: Salute)
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
				(client setScript: 0 setCycle: 0 cel: 0)
				(if (== client Mouth)
					(client hide:)
				)
				(self client: 0)
			)
		)
	)
)

(instance ethelEyes of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= seconds (Random 2 5))
			)
			(1
				(Eye loop: (Random 5 7) setCycle: EndLoop self)
				(= state -1)
			)
		)
	)
)

(instance Salute of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if (& global173 $0002)
					(Print 302 6 #dispose)
				)
				(Arm show: setMotion: MoveTo 183 99 self)
			)
			(1
				(if (not (& global173 $0002))
					(EthelPrint 302 7)
				)
				(= seconds 3)
			)
			(2
				(Arm setMotion: MoveTo 205 106 self)
			)
			(3
				(if (not (& global173 $0002))
					(cls)
				)
				(Arm setCycle: EndLoop)
				(Mouth show: cycleSpeed: 2 setCycle: Forward)
				(= seconds 2)
			)
			(4
				(Mouth hide:)
				(Arm setCel: 0 setMotion: MoveTo 199 134 self)
			)
			(5
				(client setScript: 0)
				(curRoom newRoom: prevRoomNum)
			)
		)
	)
)

(instance parrotBody of PicView
	(properties
		y 103
		x 84
		view 525
		priority 1
	)
)

(instance Arm of Actor
	(properties
		y 134
		x 199
		view 324
	)
)

(instance Ethel of Prop
	(properties
		y 106
		x 222
		view 324
		loop 3
		signal ignrAct
	)
)

(instance ParrotMouth of Prop
	(properties
		y 69
		x 92
		view 525
		loop 1
	)
)

(instance Mouth of Prop
	(properties
		y 89
		x 212
		view 324
		loop 4
		signal ignrAct
		cycleSpeed 1
	)
)

(instance Eye of Prop
	(properties
		y 74
		x 212
		view 324
		loop 5
		signal ignrAct
	)
)

(instance myMusic of Sound)
