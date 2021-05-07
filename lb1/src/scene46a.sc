;;; Sierra Script 1.0 - (do not remove this comment)
(script# 351)
(include sci.sh)
(use Main)
(use Intrface)
(use Sound)
(use Motion)
(use Game)
(use Actor)

(public
	scene46a 0
)

(local
	local0
)
(instance Snoring of Act
	(properties)
)

(instance snoring of Sound
	(properties)
)

(instance scene46a of Rm
	(properties
		picture 62
		style $0007
	)
	
	(method (init)
		(super init:)
		(HandsOff)
		(LoadMany 132 114 115)
		(snoring number: 114 loop: 1 play:)
		(if (& global173 $0002)
			(Snoring
				view: 146
				setLoop: 0
				cel: 12
				posn: 80 164
				setPri: 1
				moveSpeed: 1
				setMotion: MoveTo 176 59 self
				init:
			)
			(Print 351 0 #width 240 #dispose)
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
		(super dispose:)
	)
	
	(method (cue)
		(cls)
		(curRoom newRoom: prevRoomNum)
	)
)
