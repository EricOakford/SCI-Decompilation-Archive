;;; Sierra Script 1.0 - (do not remove this comment)
(script# 301)
(include game.sh)
(use Main)
(use Intrface)
(use Sound)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	scene38a 0
)

(local
	local0
	local1
	talkCycles
	mouthCued
	saveBits
)
(procedure (Measure &tmp [str 500])
	(GetFarText &rest @str)
	(if (< (= talkCycles (/ (StrLen @str) 2)) 20)
		(= talkCycles 20)
	)
)

(procedure (GertPrint)
	(Measure &rest)
	(gertMouth setScript: cycleMouth)
	(Print &rest
		#at 140 110
		#font 4
		#width 160
		#mode teJustCenter
		#dispose
	)
)

(procedure (ClarPrint)
	(Measure &rest)
	(/= talkCycles 2)
	(clarMouth setScript: cycleMouth)
	(Print &rest
		#at 10 110
		#font 4
		#width 160
		#mode teJustCenter
		#dispose
	)
)

(procedure (EventDone)
	(|= global173 $0002)
	(= [global368 1] 0)
	(++ global197)
	(Bset 16)
	(Bset 18)
	(Bset 21)
)

(instance scene38a of Room
	(properties
		picture 62
		style IRISOUT
	)
	
	(method (init)
		(super init:)
		(LoadMany FONT 4 41)
		(LoadMany 143 406)
		(Load VIEW 642)
		(LoadMany SOUND 29 94 95 96)
		(HandsOff)
		(Clarence setPri: 1 init:)
		(clarMouth setPri: 2 init: hide:)
		(clarEye
			setLoop: 8
			setPri: 2
			init:
			stopUpd:
			setScript: ClarsEye
		)
		(Hand setLoop: 0 setCel: 1 setPri: 1 init:)
		(Smoke setPri: 2 init: hide:)
		(myMusic number: 27 loop: -1 play:)
		(if (not (& global173 $0002))
			(EventDone)
			(if (!= [global368 1] 1)
				(gertEye setPri: 2 init: stopUpd: setScript: GertsEye)
				(gertMouth setPri: 2 init: hide:)
				(Gertie setPri: 1 init:)
				(gertGlass
					setLoop: 3
					setCel: 0
					setPri: 3
					ignoreActors: TRUE
					init:
				)
			)
			(self setScript: speech38a)
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

(instance GertsEye of Script

	(method (changeState newState)
		(switch (= state newState)
			(0
				(gertEye cel: (^ (gertEye cel?) 1) forceUpd:)
				(= state -1)
				(= seconds (Random 1 6))
			)
		)
	)
)

(instance speech38a of Script
	
	(method (doit)
		(super doit:)
		(if (>= state 1)
			(if (== (mod state 2) 0)
				(gertGlass setMotion: MoveTo 222 118)
				(if (and (== (Hand x?) 122) (== (Hand y?) 135))
					(Smoke show: setCycle: EndLoop)
				)
				(Hand setMotion: MoveTo 122 135)
			else
				(gertGlass setMotion: MoveTo 246 138)
				(Hand setMotion: MoveTo 140 190)
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
						(Display 301 0
							p_at 48 8
							p_width 256
							p_color vWHITE
							p_back -1
							p_font SYSFONT
							p_save
						)
					)
					(ClarPrint 301 1)
					(= seconds 10)
				)
				(2
					(GertPrint 301 2)
					(= seconds 10)
				)
				(3
					(ClarPrint 301 3)
					(= seconds 10)
				)
				(4
					(GertPrint 301 4)
					(= seconds 3)
				)
				(5
					(ClarPrint 301 5)
					(= seconds 10)
				)
				(6
					(GertPrint 301 6)
					(= seconds 10)
				)
				(7
					(ClarPrint 301 7)
					(= seconds 10)
				)
				(8
					(GertPrint 301 8)
					(= seconds 10)
				)
				(9
					(ClarPrint 301 9)
					(= seconds 4)
				)
				(10
					(GertPrint 301 10)
					(= seconds 4)
				)
				(11
					(gertEye hide:)
					(gertGlass hide:)
					(Gertie
						setLoop: 0
						setCel: 1
						setStep: 5 5
						setMotion: MoveTo 340 (Gertie y?) self
					)
				)
				(12
					(cls)
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
			(if (not (& global173 $0002))
				(EventDone)
			)
			(curRoom newRoom: prevRoomNum)
		)
	)
)

(instance twice of Script

	(method (changeState newState)
		(switch (= state newState)
			(0
				(Hand setMotion: MoveTo 122 135 self)
				(Print 301 11 #dispose)
				(clarMouth cycleSpeed: 1)
			)
			(1
				(= talkCycles 4)
				(clarMouth setScript: cycleMouth)
				(= cycles 1)
			)
			(2
				(if (clarMouth script?)
					(= cycles (= state 1))
				else
					(Smoke show: setCycle: EndLoop self)
					(if (< (++ local1) 2)
						(= state 0)
					)
				)
			)
			(3
				(Hand setMotion: MoveTo 140 190)
				(= cycles 10)
			)
			(4
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
				(= cycles talkCycles)
			)
			(1
				(client setScript: 0 hide:)
				(self client: 0)
			)
		)
	)
)

(instance Gertie of Actor
	(properties
		y 104
		x 213
		view 345
	)
)

(instance Clarence of Prop
	(properties
		y 115
		x 102
		view 409
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

(instance clarMouth of Prop
	(properties
		y 94
		x 114
		view 409
		loop 2
	)
)

(instance gertMouth of Prop
	(properties
		y 85
		x 213
		view 345
		loop 2
	)
)

(instance gertEye of Prop
	(properties
		y 66
		x 219
		view 345
		loop 1
	)
)

(instance gertGlass of Actor
	(properties
		y 138
		x 246
		yStep 5
		view 345
		xStep 5
	)
)

(instance Hand of Actor
	(properties
		y 190
		x 140
		yStep 5
		view 409
	)
)

(instance clarEye of Prop
	(properties
		y 72
		x 118
		view 409
	)
)

(instance myMusic of Sound)
