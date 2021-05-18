;;; Sierra Script 1.0 - (do not remove this comment)
(script# 321)
(include game.sh)
(use Main)
(use Intrface)
(use Sound)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	scene36a 0
)

(local
	local0
	local1
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
		#at 160 120
		#font 4
		#width 140
		#mode teJustCenter
		#dispose
	)
)

(procedure (RudyPrint)
	(puff hide:)
	(Measure &rest)
	(rudyMouth setScript: cycleMouth)
	(Print &rest
		#at 20 120
		#font 4
		#width 140
		#mode teJustCenter
		#dispose
	)
)

(procedure (localproc_00b2)
	(|= global173 $0001)
	(= [global368 0] 0)
	(= [global368 2] 1800)
	(Bset 23)
	(Bset 24)
)

(instance scene36a of Room
	(properties
		picture 62
		style IRISOUT
	)
	
	(method (init)
		(super init:)
		(Load FONT 4)
		(HandsOff)
		(myMusic number: 27 loop: -1 play:)
		(if (not (& global173 $0001))
			(if (== [global368 0] 1)
				(localproc_00b2)
				(= global199 1)
			else
				(Load FONT 41)
				(LoadMany 143 406)
				(Load VIEW 642)
				(LoadMany SOUND 29 94 95 96)
				(Rudy setPri: 1 init: stopUpd:)
				(rudyMouth setPri: 2 init: hide:)
				(rudyEye setPri: 2 init: stopUpd: setScript: RudysEyes)
				(self setScript: speech36a)
			)
		)
		(if (== global199 1)
			(self setScript: noOne)
		else
			(Smoke
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
			(Gloria setPri: 1 init:)
			(puff setPri: 1 init: hide:)
			(Hand
				setLoop: 1
				setCel: 0
				setPri: 3
				ignoreActors: TRUE
				init:
				stopUpd:
			)
			(if (& global173 $0001)
				(self setScript: twice)
			)
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

(instance RudysEyes of Script

	(method (changeState newState)
		(switch (= state newState)
			(0
				(rudyEye setLoop: 8 setCel: 0 forceUpd:)
				(= seconds (Random 1 3))
			)
			(1
				(if (and (not local1) (== (Random 1 2) 1))
					(rudyEye setLoop: 7 setCel: 1 forceUpd:)
					(= local1 1)
					(= cycles 1)
				else
					(rudyEye setLoop: 8 setCel: 1 forceUpd:)
					(= local1 0)
					(= seconds (Random 1 3))
				)
				(= state -1)
			)
		)
	)
)

(instance GlorsEyes of Script

	(method (changeState newState)
		(switch (= state newState)
			(0
				(= seconds (Random 2 5))
			)
			(1
				(glorEye setCycle: BegLoop self)
				(= state -1)
			)
		)
	)
)

(instance speech36a of Script
	
	(method (doit)
		(super doit:)
		(if (>= state 1)
			(cond 
				((and (== (mod state 2) 0) state (!= state 12))
					(= local0 0)
					(glorMouth loop: 3 cycleSpeed: 0)
					(Hand setMotion: MoveTo 167 111)
					(glow hide:)
					(if (and (== (Hand x?) 167) (== (Hand y?) 111))
						(Smoke posn: 148 89 show: setCycle: Forward)
					)
				)
				(
					(and
						(== (Hand x?) 186)
						(== (Hand y?) 111)
						(== local0 0)
					)
					(= local0 1)
					(glow show:)
					(glorMouth loop: 2 cycleSpeed: 3 setCycle: Forward show:)
					(Smoke hide:)
				)
				((not local0)
					(Hand setMotion: MoveTo 186 111)
					(Smoke setMotion: MoveTo 169 89)
					(glorMouth setCycle: EndLoop)
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
						((not (& global118 $0001))
							(|= global118 $0001)
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
						(Display 321 0
							p_at 48 8
							p_width 256
							p_color vWHITE
							p_back -1
							p_font SYSFONT
							p_save
						)
					)
					(RudyPrint 321 1)
					(= seconds 10)
				)
				(2
					(GlorPrint 321 2)
					(= seconds 4)
				)
				(3
					(RudyPrint 321 3)
					(= seconds 7)
				)
				(4
					(GlorPrint 321 4)
					(= seconds 10)
				)
				(5
					(RudyPrint 321 5)
					(= seconds 8)
				)
				(6
					(GlorPrint 321 6)
					(= seconds 10)
				)
				(7
					(RudyPrint 321 7)
					(= seconds 8)
				)
				(8
					(GlorPrint 321 8)
					(= seconds 10)
				)
				(9
					(RudyPrint 321 9)
					(= seconds 8)
				)
				(10
					(GlorPrint 321 10)
					(= seconds 10)
				)
				(11
					(RudyPrint 321 11)
					(= seconds 8)
				)
				(12
					(cls)
					(Rudy
						setLoop: 1
						setStep: 5 5
						setMotion: MoveTo -40 (Rudy y?) self
					)
					(rudyEye hide:)
				)
				(13
					(localproc_00b2)
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
			(if (not (& global173 $0001))
				(localproc_00b2)
			)
			(curRoom newRoom: prevRoomNum)
		)
	)
)

(instance twice of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(Smoke posn: 148 89 show: setCycle: Forward)
				(= cycles 1)
			)
			(1
				(if (== global199 2)
					(Print 321 12 #dispose)
				else
					(Print 321 13 #dispose)
				)
				(= seconds 4)
			)
			(2
				(cls)
				(Smoke setMotion: MoveTo 169 89)
				(Hand setMotion: MoveTo 186 111 self)
			)
			(3
				(curRoom newRoom: prevRoomNum)
			)
		)
	)
)

(instance noOne of Script

	(method (changeState newState)
		(switch (= state newState)
			(0
				(Print 321 14 #at 65 67 #dispose)
				(= seconds 5)
			)
			(1
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

(instance Gloria of Prop
	(properties
		y 110
		x 231
		view 367
		signal ignrAct
	)
)

(instance Rudy of Actor
	(properties
		y 111
		x 97
		view 391
	)
)

(instance Smoke of Actor
	(properties
		y 89
		x 148
		yStep 5
		view 367
		xStep 5
	)
)

(instance rudyMouth of Prop
	(properties
		y 95
		x 106
		view 391
		loop 2
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

(instance rudyEye of Prop
	(properties
		y 72
		x 106
		view 391
		loop 8
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
		signal $4000
	)
)

(instance Hand of Actor
	(properties
		y 111
		x 167
		yStep 5
		view 367
		xStep 5
	)
)

(instance myMusic of Sound)
