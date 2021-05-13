;;; Sierra Script 1.0 - (do not remove this comment)
(script# 304)
(include game.sh)
(use Main)
(use Intrface)
(use Sound)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	scene38e 0
)

(local
	local0
)
(instance Clarence of Prop)

(instance Torso of Prop)

(instance Eyes of Prop)

(instance Mouth of Prop)

(instance Hand of Actor)

(instance myMusic of Sound)

(instance scene38e of Room
	(properties
		picture 62
		style IRISOUT
	)
	
	(method (init)
		(super init:)
		(Load FONT 41)
		(LoadMany 143 406)
		(Load VIEW 642)
		(LoadMany SOUND 29 94 95 96)
		(HandsOff)
		(myMusic number: 27 loop: -1 play:)
		(Clarence
			view: 415
			loop: 0
			cel: 0
			posn: 103 86
			setPri: 1
			init:
		)
		(Torso
			view: 415
			loop: 1
			cel: 0
			posn: 103 107
			setPri: 1
			ignoreActors: TRUE
			init:
		)
		(Eyes
			view: 415
			loop: 2
			cel: 0
			posn: 99 67
			setPri: 2
			setScript: ClarsEyes
			init:
		)
		(Mouth
			view: 415
			loop: 6
			cel: 0
			posn: 99 85
			setPri: 2
			init:
			hide:
		)
		(Hand
			view: 415
			setLoop: 7
			setCel: 0
			setPri: 3
			yStep: 5
			posn: 71 142
			init:
			hide:
		)
		(self setScript: twice)
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

(instance twice of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(cond 
					((not global216)
						(= state -1)
					)
					((not (& global118 $0008))
						(|= global118 $0008)
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
				(Print 304 0 #width 230 #dispose)
				(Hand show: setMotion: MoveTo 93 120 self)
			)
			(2
				(Eyes hide:)
				(Clarence startUpd: setCycle: EndLoop)
				(Hand setCycle: EndLoop setMotion: MoveTo 95 108 self)
			)
			(3
				(Hand stopUpd:)
				(Mouth show: setCycle: Forward)
				(= cycles 4)
			)
			(4
				(Mouth hide:)
				(Clarence setCycle: BegLoop)
				(Hand setCycle: BegLoop setMotion: MoveTo 93 120 self)
			)
			(5
				(Eyes show:)
				(Clarence stopUpd:)
				(Hand setMotion: MoveTo 71 142 self)
			)
			(6
				(curRoom newRoom: prevRoomNum)
			)
		)
	)
)

(instance ClarsEyes of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(Eyes cel: (^ (Eyes cel?) 1) forceUpd:)
				(= state -1)
				(if (Eyes cel?)
					(Eyes loop: (Random 2 5))
					(= cycles 2)
				else
					(= seconds (Random 1 3))
				)
			)
		)
	)
)
