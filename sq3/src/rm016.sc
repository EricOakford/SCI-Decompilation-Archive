;;; Sierra Script 1.0 - (do not remove this comment)
(script# 16)
(include game.sh)
(use Main)
(use Intrface)
(use Motion)
(use Game)
(use Menu)
(use Actor)
(use System)

(public
	rm016 0
)

(procedure (ComputerDisplay message theX theY theColor)
	(if (< numColors 16) (= theColor vWHITE))
	(Display message
		p_width 310
		p_at theX theY
		p_font 600
		p_color theColor
		p_mode teJustCenter
	)
)

(procedure (DiagnotiscsDisplay message theX theY theColor)
	(if (< numColors 16) (= theColor vWHITE))
	(Display message
		p_at theX theY
		p_font 600
		p_color theColor
	)
)

(instance rm016 of Room
	(properties
		picture 161
		style HSHUTTER
	)
	
	(method (init &tmp [temp0 50])
		(self setRegions: TRAVEL)
		(HandsOff)
		(= inCartoon 1)
		(TheMenuBar hide:)
		(StatusLine disable:)
		(Load VIEW 40)
		(Load VIEW 42)
		(Load PICTURE 161)
		(Load PICTURE 162)
		(Load PICTURE 163)
		(Load PICTURE 164)
		(super init:)
		(if
			(and
				((inventory at: iReactor) ownedBy: 14)
				((inventory at: iWire) ownedBy: 14)
			)
			(= shipRepairLevel 4)
			(self setScript: ReactorIn)
		else
			(= shipRepairLevel 1)
			(self setScript: ReactorNotIn)
		)
		(= saveDisabled TRUE)
	)
	
	(method (doit)
		(super doit:)
	)
	
	(method (handleEvent event)
		(if (event claimed?) (return))
		(if (event type?)
			(= inCartoon 0)
			(event claimed: TRUE)
			(= saveDisabled FALSE)
			(curRoom newRoom: 14)
		)
	)
)

(instance ReactorNotIn of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ComputerDisplay {ACCESS DENIED} 1 25 vRED)
				(ComputerDisplay {-----------} 1 31 vGREEN)
				(ComputerDisplay
					{>> POWER CRITICALLY LOW <<\n\n AUX. REACTOR NOT ON-LINE}
					1 55 vLRED
				)
				(ComputerDisplay
					{INSUFFICIENT POWER TO COMMENCE\n____WITH SYSTEMS CHECK}
					1 85 vLRED
				)
				(ComputerDisplay
					{USING STORED POWER\n___BELOW 10%}
					1 155 vYELLOW
				)
				(= seconds 5)
			)
			(1
				(= inCartoon 0)
				(HandsOn)
				(= saveDisabled FALSE)
				(curRoom newRoom: 14)
			)
		)
	)
)

(instance ReactorIn of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ComputerDisplay {SYSTEMS CHECK} 1 25 vWHITE)
				(ComputerDisplay {IN PROGRESS} 1 35 vWHITE)
				(ComputerDisplay
					{>> POWER LEVEL NOMINAL <<\n\n___AUX. REACTOR ON-LINE}
					1 55 vLGREEN
				)
				(= seconds 5)
			)
			(1
				(curRoom drawPic: 164)
				(DiagnotiscsDisplay {AUXILIARY REACTOR:} 100 162 vYELLOW)
				(diagFlash1
					loop: 4
					cel: 0
					posn: 151 114
					init:
					setCycle: Forward
				)
				(= seconds 4)
			)
			(2
				(DiagnotiscsDisplay {AUXILIARY REACTOR:} 100 162 vGREEN)
				(DiagnotiscsDisplay {NOMINAL} 212 162 vLGREEN)
				(= seconds 3)
			)
			(3
				(curRoom drawPic: 162)
				(diagFlash1
					loop: 1
					cel: 0
					posn: 107 119
					init:
					setCycle: Forward
				)
				(diagFlash2
					loop: 0
					cel: 0
					posn: 182 137
					init:
					setCycle: Forward
				)
				(DiagnotiscsDisplay {LANDING GEAR} 100 162 vYELLOW)
				(= seconds 3)
			)
			(4
				(DiagnotiscsDisplay {LANDING GEAR} 100 162 vGREEN)
				(DiagnotiscsDisplay {NOMINAL} 212 162 vLGREEN)
				(= seconds 3)
			)
			(5
				(diagFlash1 hide:)
				(diagFlash2 hide:)
				(if (== motivatorState motivatorINSHIP)
					(curRoom setScript: MotivIn)
				else
					(curRoom setScript: MotivNotIn)
				)
			)
		)
	)
)

(instance MotivIn of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(curRoom drawPic: 163)
				(diagArea loop: 2 posn: 239 108 setPri: 7 stopUpd:)
				(DiagnotiscsDisplay {WARP MOTIVATOR:} 89 150 vYELLOW)
				(diagArea init: stopUpd:)
				(diagFlash1
					setLoop: 3
					cel: 0
					posn: 233 99
					setPri: 15
					show:
					cycleSpeed: 1
					setCycle: Forward
				)
				(= seconds 3)
			)
			(1
				(DiagnotiscsDisplay {WARP MOTIVATOR_} 89 150 vGREEN)
				(DiagnotiscsDisplay {NOMINAL} 100 162 vLGREEN)
				(= seconds 4)
			)
			(2
				(= inCartoon 0)
				(Print 16 0)
				(HandsOn)
				(= saveDisabled FALSE)
				(curRoom newRoom: 14)
			)
		)
	)
)

(instance MotivNotIn of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(curRoom drawPic: 163)
				(diagArea loop: 2 posn: 239 108 setPri: 7 stopUpd:)
				(= cycles 2)
			)
			(1
				(DiagnotiscsDisplay {WARP MOTIVATOR:} 89 150 14)
				(diagArea init: stopUpd:)
				(diagFlash1
					setLoop: 3
					cel: 0
					posn: 233 99
					setPri: 15
					show:
					cycleSpeed: 1
					setCycle: Forward
				)
				(= seconds 4)
			)
			(2
				(diagEnlarge posn: 233 93 setLoop: 0 cel: 0 init:)
				(= seconds 1)
			)
			(3
				(diagFlash2
					posn: (diagEnlarge xLast?) (diagEnlarge yLast?)
					loop: 0
					cel: (diagEnlarge cel?)
					init:
					setCycle: 0
				)
				(diagEnlarge
					cel: (+ (diagEnlarge cel?) 1)
					posn: 218 102
				)
				(= cycles 3)
			)
			(4
				(diagFlash2
					posn: (diagEnlarge xLast?) (diagEnlarge yLast?)
					loop: 0
					cel: (diagEnlarge cel?)
				)
				(diagEnlarge
					cel: (+ (diagEnlarge cel?) 1)
					posn: 200 110
				)
				(= cycles 3)
			)
			(5
				(diagFlash2
					posn: (diagEnlarge xLast?) (diagEnlarge yLast?)
					loop: 0
					cel: (diagEnlarge cel?)
				)
				(diagEnlarge
					cel: (+ (diagEnlarge cel?) 1)
					posn: 172 119
				)
				(= cycles 3)
			)
			(6
				(diagFlash2
					posn: (diagEnlarge xLast?) (diagEnlarge yLast?)
					loop: 0
					cel: (diagEnlarge cel?)
				)
				(diagEnlarge
					cel: (+ (diagEnlarge cel?) 1)
					posn: 142 130
				)
				(= cycles 3)
			)
			(7
				(diagFlash2
					posn: (diagEnlarge xLast?) (diagEnlarge yLast?)
					loop: 0
					cel: (diagEnlarge cel?)
				)
				(diagEnlarge
					cel: (+ (diagEnlarge cel?) 1)
					posn: 101 145
				)
				(= cycles 3)
			)
			(8
				(diagFlash2
					posn: (diagEnlarge xLast?) (diagEnlarge yLast?)
					loop: 0
					cel: (diagEnlarge cel?)
				)
				(diagEnlarge
					setLoop: 1
					cel: 0
					posn: 47 184
					cycleSpeed: 2
					setCycle: Forward
				)
				(= motivatorKnown TRUE)
				(= cycles 3)
			)
			(9
				(diagFlash2 hide:)
				(= seconds 2)
			)
			(10
				(DiagnotiscsDisplay {WARP MOTIVATOR_} 89 150 vRED)
				(DiagnotiscsDisplay {MALFUNCTION} 100 162 vLRED)
				(DiagnotiscsDisplay {UNIT NOT INSTALLED} 89 173 vYELLOW)
				(= seconds 7)
			)
			(11
				(= inCartoon 0)
				(HandsOn)
				(= saveDisabled FALSE)
				(curRoom newRoom: 14)
			)
		)
	)
)

(instance diagFlash1 of Actor
	(properties)
	
	(method (init)
		(super init:)
		(self view: 40 ignoreActors: TRUE)
	)
)

(instance diagFlash2 of Actor
	(properties)
	
	(method (init)
		(super init:)
		(self view: 40 ignoreActors: TRUE)
	)
)

(instance diagArea of Actor
	(properties)
	
	(method (init)
		(super init:)
		(self view: 40 ignoreActors: TRUE)
	)
)

(instance diagEnlarge of Actor
	(properties)
	
	(method (init)
		(super init:)
		(self view: 42 ignoreActors: TRUE)
	)
)
