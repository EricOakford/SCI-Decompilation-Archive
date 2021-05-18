;;; Sierra Script 1.0 - (do not remove this comment)
(script# 333)
(include game.sh)
(use Main)
(use Intrface)
(use Sound)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	scene48c 0
)

(local
	theCycles
	mouthCued
	saveBits
)
(procedure (Measure &tmp [str 500])
	(GetFarText &rest @str)
	(= theCycles (+ (/ (StrLen @str) 3) 1))
)

(procedure (ClarPrint)
	(Measure &rest)
	(clarMouth setScript: cycleMouth)
	(Print &rest
		#at 160 110
		#font 4
		#width 140
		#mode teJustCenter
		#dispose
	)
)

(procedure (RudyPrint)
	(Measure &rest)
	(rudyMouth setScript: cycleMouth)
	(Print &rest
		#at 10 120
		#font 4
		#width 140
		#mode teJustCenter
		#dispose
	)
)

(procedure (localproc_008d)
	(|= global173 $0008)
	(= [global368 2] 1)
	(Bset 23)
)

(instance scene48c of Room
	(properties
		picture 62
		style IRISOUT
	)
	
	(method (init)
		(super init:)
		(HandsOff)
		(if (& global173 $0008)
			(LoadMany SOUND 114 115)
			(snoring number: 114 loop: 1 play:)
			(Snoring
				setLoop: 0
				setPri: 1
				moveSpeed: 2
				setMotion: MoveTo 176 59 self
				init:
			)
			(Print 333 0 #width 240 #dispose)
		else
			(Load FONT 4)
			(snoring number: 27 loop: -1 play:)
			(clarMouth setPri: 2 init:)
			(Clarence setPri: 1 ignoreActors: TRUE init:)
			(clarEye setPri: 2 init: stopUpd: setScript: ClarsEye)
			(Rudy setPri: 1 init:)
			(rudyMouth setPri: 2 init:)
			(rudyEye setPri: 2 init: stopUpd: setScript: RudysEyes)
			(Hand
				setLoop: 7
				setPri: 3
				xStep: 5
				yStep: 5
				ignoreActors: TRUE
			)
			(self setScript: speech48c)
		)
	)
	
	(method (doit)
		(super doit:)
		(if
			(and
				(== (snoring prevSignal?) -1)
				(== (snoring number?) 114)
			)
			(snoring number: 115 loop: 1 prevSignal: 0 play:)
		)
	)
	
	(method (dispose)
		(localproc_008d)
		(super dispose:)
	)
	
	(method (handleEvent event)
		(super handleEvent: event)
	)
	
	(method (cue)
		(cls)
		(curRoom newRoom: prevRoomNum)
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

(instance RudysEyes of Script

	(method (changeState newState)
		(switch (= state newState)
			(0
				(rudyEye cel: (Random 0 2) forceUpd:)
				(= state -1)
				(if (== (rudyEye cel?) 2)
					(= cycles 2)
				else
					(= seconds (Random 2 5))
				)
			)
		)
	)
)

(instance speech48c of Script

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
						((not (& global118 $0004))
							(|= global118 $0004)
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
						(Display 333 1
							p_at 48 8
							p_width 256
							p_color vWHITE
							p_back -1
							p_font SYSFONT
							p_save
						)
					)
					(ClarPrint 333 2)
					(= seconds 10)
				)
				(2
					(RudyPrint 333 3)
					(= seconds 6)
				)
				(3
					(= cycles 1)
				)
				(4
					(RudyPrint 333 4)
					(= seconds 7)
				)
				(5
					(ClarPrint 333 5)
					(= seconds 7)
				)
				(6
					(RudyPrint 333 6)
					(= seconds 10)
				)
				(7
					(ClarPrint 333 7)
					(= seconds 8)
				)
				(8
					(Hand init: setMotion: MoveTo 161 100)
					(RudyPrint 333 8)
					(= seconds 10)
				)
				(9
					(Hand init: setMotion: MoveTo 161 130 self)
				)
				(10
					(cls)
					(Rudy
						setLoop: 0
						setCycle: Walk
						setStep: 5 5
						setMotion: MoveTo 340 (Rudy y?) self
					)
					(rudyEye hide:)
					(rudyMouth stopUpd: hide:)
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

(instance Rudy of Actor
	(properties
		y 113
		x 103
		view 391
	)
)

(instance rudyMouth of Prop
	(properties
		y 97
		x 112
		view 391
		loop 2
	)
)

(instance rudyEye of Prop
	(properties
		y 74
		x 112
		view 391
		loop 8
	)
)

(instance Hand of Actor
	(properties
		y 130
		x 161
		view 391
	)
)

(instance Clarence of Actor
	(properties
		y 117
		x 217
		view 409
		loop 1
	)
)

(instance clarEye of Prop
	(properties
		y 74
		x 202
		view 409
		loop 9
	)
)

(instance clarMouth of Prop
	(properties
		y 96
		x 206
		view 409
		loop 3
	)
)

(instance Snoring of Actor
	(properties
		y 164
		x 80
		view 146
		cel 12
	)
)

(instance snoring of Sound)
