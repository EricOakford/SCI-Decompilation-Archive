;;; Sierra Script 1.0 - (do not remove this comment)
(script# 303)
(include game.sh)
(use Main)
(use Intrface)
(use Sound)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	scene34d 0
)

(local
	talkCycles
	mouthCued
	saveBits
	local3 =  1
	eyeCycles
)
(procedure (Measure &tmp [str 500])
	(GetFarText &rest @str)
	(= talkCycles (+ (/ (StrLen @str) 2) 1))
)

(procedure (localproc_002c tOrF)
	(if (= local3 tOrF)
		(jeevEyes hide:)
		(jeevFace show:)
	else
		(jeevEyes show:)
		(jeevFace hide:)
	)
)

(procedure (FifiPrint)
	(Measure &rest)
	(fifiMouth setScript: cycleMouth)
	(Print &rest
		#at 20 120
		#font 4
		#width 140
		#mode teJustCenter
		#dispose
	)
)

(procedure (JeevPrint)
	(Measure &rest)
	(jeevMouth setScript: cycleMouth)
	(Print &rest
		#at 160 120
		#font 4
		#width 140
		#mode teJustCenter
		#dispose
	)
)

(instance scene34d of Room
	(properties
		picture 62
		style IRISOUT
	)
	
	(method (init)
		(super init:)
		(Load FONT 4)
		(HandsOff)
		(myMusic number: 27 loop: -1 play:)
		(if (and (not (& global173 $0020)) (> [global368 2] 1))
			(|= global173 $0020)
			(= [global368 2] 1)
			(Fifi setPri: 3 cycleSpeed: 2 init:)
			(fifiMouth cycleSpeed: 1 setPri: 4 init: hide:)
			(jeevEyes setPri: 2 cycleSpeed: 2 init: hide:)
			(jeevMouth setPri: 2 init: hide:)
			(jeevFace setPri: 3 cycleSpeed: 1 init: hide:)
			(Jeeves setPri: 1 init:)
			(self setScript: speech34d)
		else
			(Jeeves setPri: 1 init:)
			(jeevEyes
				setPri: 2
				cycleSpeed: 2
				setScript: JeevEyes
				init:
			)
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

(instance speech34d of Script

	(method (doit)
		(super doit:)
		(if (and (not local3) (<= (-- eyeCycles) 0))
			(= eyeCycles (Random 10 30))
			(if (jeevEyes cycler?)
				(jeevEyes setCycle: 0 hide:)
			else
				(jeevEyes setCycle: Forward show:)
			)
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= saveBits
					(Display 303 0
						p_at 48 8
						p_width 256
						p_color vWHITE
						p_back -1
						p_font SYSFONT
						p_save
					)
				)
				(Fifi setCycle: EndLoop self)
				(jeevFace show: setCycle: EndLoop)
			)
			(1
				(Fifi setCycle: BegLoop self)
				(jeevFace setCycle: BegLoop)
			)
			(2
				(localproc_002c 0)
				(JeevPrint 303 1)
				(= seconds 4)
			)
			(3
				(FifiPrint 303 2)
				(= seconds 4)
			)
			(4
				(Fifi setCycle: EndLoop self)
				(localproc_002c 1)
				(jeevFace setCycle: EndLoop)
			)
			(5
				(Fifi setCycle: BegLoop self)
				(jeevFace setCycle: BegLoop)
			)
			(6
				(localproc_002c 0)
				(JeevPrint 303 3)
				(= seconds 4)
			)
			(7
				(FifiPrint 303 4)
				(= seconds 4)
			)
			(8
				(FifiPrint 303 5)
				(= seconds 4)
			)
			(9
				(JeevPrint 303 6)
				(= seconds 4)
			)
			(10
				(FifiPrint 303 7)
				(= seconds 4)
			)
			(11
				(Fifi setCycle: EndLoop self)
				(localproc_002c 1)
				(jeevFace setCycle: EndLoop)
			)
			(12
				(cls)
				(Fifi setCycle: BegLoop self)
				(jeevFace setCycle: BegLoop)
			)
			(13
				(localproc_002c 0)
				(Fifi
					setLoop: 5
					posn: (- (Fifi x?) 43) (Fifi y?)
					setCycle: Walk
					setMotion: MoveTo -2 (Fifi y?) self
				)
				(Jeeves stopUpd:)
			)
			(14
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
				(client setScript: 0 hide:)
				(self client: 0)
			)
		)
	)
)

(instance JeevEyes of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= state -1)
				(if (^= local3 TRUE)
					(jeevEyes hide:)
					(= seconds (Random 2 3))
				else
					(jeevEyes
						cel: (/ (Random 1 29999) 10000)
						forceUpd:
						show:
					)
					(= cycles 3)
				)
			)
		)
	)
)

(instance twice of Script

	(method (changeState newState)
		(switch (= state newState)
			(0
				(Print 303 8 #dispose)
				(= seconds 4)
			)
			(1
				(curRoom newRoom: prevRoomNum)
			)
		)
	)
)

(instance Jeeves of Prop
	(properties
		y 108
		x 192
		view 453
	)
)

(instance jeevFace of Prop
	(properties
		y 93
		x 188
		view 453
		loop 3
	)
)

(instance jeevMouth of Prop
	(properties
		y 93
		x 188
		view 453
		loop 1
	)
)

(instance jeevEyes of Prop
	(properties
		y 77
		x 188
		view 453
		loop 2
	)
)

(instance Fifi of Actor
	(properties
		y 102
		x 172
		view 453
		loop 4
		illegalBits $0000
	)
)

(instance fifiMouth of Prop
	(properties
		y 85
		x 162
		view 453
		loop 6
	)
)

(instance myMusic of Sound)
