;;; Sierra Script 1.0 - (do not remove this comment)
(script# 350)
(include game.sh)
(use Main)
(use Intrface)
(use Sound)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	scene42a 0
)

(local
	theCycles
	mouthCued
	saveBits
	theFifi
	local4 =  1
	local5
)
(procedure (Measure &tmp [str 500])
	(GetFarText &rest @str)
	(= theCycles (+ (/ (StrLen @str) 3) 1))
)

(procedure (localproc_02a4 tOrF)
	(if (= local4 tOrF)
		(coloEyes hide:)
	else
		(coloEyes show:)
	)
)

(procedure (FifiPrint)
	(Measure &rest)
	(fifiMouth setScript: cycleMouth)
	(Print &rest
		#at 160 115
		#font 4
		#width 140
		#mode teJustCenter
		#dispose
	)
)

(procedure (ColoPrint)
	(Measure &rest)
	(= theCycles (+ theCycles (/ theCycles 4)))
	(coloMouth setScript: cycleMouth)
	(Print &rest
		#at 20 115
		#font 4
		#width 140
		#mode teJustCenter
		#dispose
	)
)

(instance Fifi of Actor)

(instance Smoke of Actor)

(instance fifiMouth of Prop)

(instance Colonel of Prop)

(instance coloFace of Prop)

(instance coloMouth of Prop)

(instance coloEyes of Prop)

(instance myMusic of Sound)

(instance scene42a of Room
	(properties
		picture 62
		style IRISOUT
	)
	
	(method (init)
		(super init:)
		(Load FONT 4)
		(HandsOff)
		(myMusic number: 27 loop: -1 play:)
		(if (and (not (& global173 $0040)) (!= [global368 3] 1))
			(|= global173 $0040)
			(= global124 1)
			(Load FONT 41)
			(LoadMany 143 406)
			(Load VIEW 642)
			(LoadMany SOUND 94 95 96 29)
			(coloEyes
				view: 303
				loop: 4
				cel: 0
				posn: 147 80
				setPri: 5
				cycleSpeed: 2
				init:
				hide:
			)
			(coloMouth
				view: 303
				loop: 3
				cel: 0
				posn: 145 88
				setPri: 2
				init:
				hide:
			)
			(coloFace
				view: 303
				loop: 5
				cel: 0
				posn: 146 88
				setPri: 3
				cycleSpeed: 1
				init:
				hide:
			)
			(Colonel
				view: 305
				posn: 140 102
				loop: 2
				cel: 0
				setPri: 1
				cycleSpeed: 1
				ignoreActors: TRUE
				init:
			)
			(Fifi
				view: 305
				loop: 0
				cel: 0
				posn: 162 102
				setPri: 3
				cycleSpeed: 2
				init:
			)
			(fifiMouth
				view: 303
				loop: 2
				cel: 0
				posn: 173 85
				cycleSpeed: 1
				setPri: 4
				init:
				hide:
			)
			(self setScript: speech42a)
		else
			(Colonel
				view: 311
				posn: 101 105
				loop: 1
				cel: 0
				setPri: 1
				init:
				stopUpd:
			)
			(coloFace
				view: 311
				posn: 114 88
				loop: 0
				cel: 0
				setPri: 2
				init:
				stopUpd:
			)
			(coloEyes
				view: 311
				posn: 114 (- (coloFace y?) 15)
				loop: 2
				cel: 0
				setPri: 3
				setScript: ColoEyes
				init:
				stopUpd:
			)
			(coloMouth
				view: 311
				posn: 114 88
				loop: 4
				cel: 0
				setPri: 2
				cycleSpeed: 1
				init:
				hide:
			)
			((= theFifi Fifi)
				view: 311
				posn: 128 136
				setLoop: 1
				setCel: 1
				setPri: 3
				moveSpeed: 1
				illegalBits: 0
				ignoreActors: TRUE
				init:
				hide:
			)
			(Smoke
				view: 311
				setLoop: 3
				setCycle: Walk
				setPri: 3
				illegalBits: 0
				ignoreActors: TRUE
				init:
				hide:
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

(instance speech42a of Script
	
	(method (doit)
		(super doit:)
		(if (and (not local4) (<= (-- local5) 0))
			(= local5 (Random 10 30))
			(if (coloEyes cycler?)
				(coloEyes setCycle: 0 hide:)
			else
				(coloEyes setCycle: Forward show:)
			)
		)
		(if (== state 14)
			(cond 
				(
				(and (not (fifiMouth script?)) (not (Fifi mover?)))
					(Fifi view: 305 setLoop: 1 x: (+ (Fifi x?) 43))
					(Fifi
						setCel: (Fifi lastCel:)
						setCycle: Walk
						setMotion: MoveTo 325 102 self
					)
				)
				((> (Fifi x?) 245) (cls))
			)
		)
	)
	
	(method (changeState newState)
		(if (cycleMouth client?)
			(= mouthCued 1)
			(= cycles 1)
		else
			(switch (= state newState)
				(0
					(cond 
						((not global216) (= state -1))
						((not (& global118 $0008))
							(|= global118 $0008)
							(self setScript: (ScriptID 406 0))
							(= state -1)
						)
						((self script?) (= state -1))
					)
					(= cycles 1)
				)
				(1
					(= saveBits
						(Display 350 0
							p_at 48 8
							p_width 256
							p_color vWHITE
							p_back -1
							p_font SYSFONT
							p_save
						)
					)
					(Colonel setCycle: EndLoop)
					(Fifi setCycle: EndLoop self)
				)
				(2
					(localproc_02a4 0)
					(Colonel view: 303 loop: 1 cel: 0)
					(Fifi view: 303 loop: 0 cel: 0)
					(FifiPrint 350 1)
					(= seconds 7)
				)
				(3
					(localproc_02a4 1)
					(cls)
					(Colonel setCycle: EndLoop self)
				)
				(4
					(coloFace cel: 0 show: setCycle: EndLoop)
					(Fifi setCycle: EndLoop self)
				)
				(5
					(Fifi setCycle: BegLoop self)
					(coloFace hide:)
				)
				(6
					(Colonel setCycle: BegLoop self)
				)
				(7
					(localproc_02a4 0)
					(ColoPrint 350 2)
					(= seconds 7)
				)
				(8
					(FifiPrint 350 3)
					(= seconds 7)
				)
				(9
					(localproc_02a4 1)
					(cls)
					(Colonel setCycle: EndLoop self)
				)
				(10
					(coloFace cel: 0 show: setCycle: EndLoop)
					(Fifi setCycle: EndLoop self)
				)
				(11
					(Fifi setCycle: BegLoop self)
					(coloFace hide:)
				)
				(12
					(Colonel setCycle: BegLoop self)
				)
				(13
					(localproc_02a4 0)
					(ColoPrint 350 4)
					(= seconds 10)
				)
				(14
					(Colonel stopUpd:)
					(FifiPrint 350 5)
				)
				(15
					(++ global192)
					(|= global173 $0040)
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
				(= cycles theCycles)
			)
			(1
				(client setScript: 0 hide:)
				(self client: 0)
			)
		)
	)
)

(instance ColoEyes of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= state -1)
				(if (^= local4 $0001)
					(coloEyes hide:)
					(= seconds (Random 2 3))
				else
					(coloEyes
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
	
	(method (doit)
		(super doit:)
		(if
			(and
				(== state 3)
				(== (Smoke cel?) (- (NumCels Smoke) 1))
			)
			(Smoke hide:)
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(Print 350 6 #dispose)
				(coloFace cel: 0 forceUpd:)
				(coloEyes y: (- (coloFace y?) 15) forceUpd:)
				(theFifi show: setMotion: MoveTo 116 116 self)
			)
			(1
				(theFifi stopUpd:)
				(coloMouth show: setCycle: Forward)
				(= seconds 3)
			)
			(2
				(coloMouth setCycle: EndLoop)
				(theFifi setMotion: MoveTo 128 136 self)
			)
			(3
				(theFifi hide:)
				(coloFace cel: 1 forceUpd:)
				(coloMouth hide:)
				(coloEyes y: (- (coloFace y?) 16) forceUpd:)
				(Smoke
					show:
					cel: 0
					posn: 113 79
					setMotion: MoveTo 128 101 self
				)
			)
			(4
				(curRoom newRoom: prevRoomNum)
			)
		)
	)
)
