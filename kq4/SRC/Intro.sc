;;; Sierra Script 1.0 - (do not remove this comment)
(script# 120)
(include game.sh)
(use Main)
(use Intrface)
(use Sound)
(use Motion)
(use Game)
(use User)
(use Actor)
(use System)

(public
	intro 0
	introSc 1
)

(local
	introTimer
	[local1 17]
	rosella
	king
	queen
	son
	hat
	[local23 7]
	introDialog
	kingCloseup
	[local32 202]
)
(instance openMusic of Sound
	(properties)
)

(instance intro of Room
	(properties
		horizon 40
	)
	
	(method (init)
		(Load VIEW 750)
		(Load VIEW 752)
		(Load VIEW 755)
		(Load VIEW 756)
		(Load VIEW 757)
		(Load VIEW 758)
		(Load VIEW 759)
		(Load VIEW 760)
		(Load VIEW 761)
		(Load VIEW 762)
		(Load VIEW 766)
		(Load VIEW 767)
		(Load VIEW 768)
		(Load VIEW 769)
		(Load PICTURE 201)
		(Load PICTURE 202)
		(Load PICTURE 203)
		(Load PICTURE 204)
		(Load PICTURE 205)
		(self setRegions: INTRO)
		(super init:)
		(= introScript introSc)
		(curRoom setScript: introSc)
		(= userFont smallFont)
	)
	
	(method (handleEvent event)
		(cond 
			(
				(and
					(== (event type?) keyDown)
					(== (event message?) `#2)
				)
				(DoSound SoundOn (not (DoSound SoundOn)))
			)
			(
				(and
					(== (event type?) keyDown)
					(== (event message?) ENTER)
				)
				(theGame restart:)
			)
		)
	)
	
	(method (newRoom newRoomNumber)
		(if (IsObject introDialog) (introDialog dispose:))
		(super newRoom: newRoomNumber)
	)
)

(instance introSc of Script
	(properties)
	
	(method (changeState newState &tmp temp0)
		(addToPics dispose:)
		(switch (= state newState)
			(0
				(User canControl: FALSE canInput: FALSE)
				(curRoom drawPic: 201)
				(openMusic number: 103 play:)
				(= king
					((Actor new:)
						view: 750
						loop: 0
						posn: 130 130
						stopUpd:
						init:
						yourself:
					)
				)
				(= queen
					((Actor new:)
						view: 769
						loop: 2
						posn: 140 119
						stopUpd:
						init:
						yourself:
					)
				)
				(= son
					((Actor new:)
						view: 760
						posn: 220 148
						loop: 1
						cel: 5
						stopUpd:
						init:
						yourself:
					)
				)
				(= rosella
					((Actor new:)
						view: 759
						loop: 1
						posn: 212 135
						stopUpd:
						init:
						yourself:
					)
				)
				(= hat
					((Actor new:)
						view: 767
						posn: 48 120
						xStep: 4
						yStep: 2
						loop: 0
						cycleSpeed: 0
						cel: 3
						stopUpd:
						init:
						yourself:
					)
				)
				((View new:)
					view: 768
					posn: 158 64
					setPri: 0
					ignoreActors:
					addToPic:
				)
				((View new:)
					view: 769
					loop: 3
					posn: 48 139
					setPri: 0
					ignoreActors:
					addToPic:
				)
				(Timer setReal: self 2)
			)
			(1
				(= introDialog (Print 120 0 #at -1 10 #width 300 #dispose))
				(Timer setReal: self 10)
			)
			(2
				(king setCycle: Walk setMotion: MoveTo 145 140 self)
			)
			(3
				(king setMotion: MoveTo 48 144 self)
			)
			(4
				(hat hide:)
				(king setMotion: MoveTo 130 140 self)
			)
			(5
				(if modelessDialog (modelessDialog dispose:))
				(king setMotion: MoveTo 134 131 self)
			)
			(6
				(king setLoop: 0)
				(= introDialog (Print 120 1 #width 300 #at -1 12 #dispose))
				(= seconds 11)
			)
			(7
				(king view: 750 setLoop: 2 cel: 255 setCycle: EndLoop self)
			)
			(8
				(hat
					show:
					posn: (+ (king x?) 10) (- (king y?) 20)
					setCycle: Reverse
					moveSpeed: 1
					ignoreActors:
					illegalBits: 0
					setMotion: MoveTo 220 70 self
				)
				(son
					view: 761
					setLoop: 0
					setCycle: Walk
					setMotion: MoveTo 275 145
				)
				(rosella
					view: 761
					setLoop: 1
					setCycle: Walk
					setMotion: MoveTo 275 135
				)
			)
			(9
				(king setLoop: 3 setCycle: EndLoop stopKing)
				(= seconds 1)
			)
			(10
				(hat stopUpd:)
				(= seconds 1)
			)
			(11
				(if modelessDialog (modelessDialog dispose:))
				(cast eachElementDo: #hide)
				(curRoom drawPic: 202)
				(hat
					show:
					setLoop: 1
					setCel: 0
					posn: 105 99
					ignoreActors:
					setStep: 4 2
					moveSpeed: 0
					cycleSpeed: 0
					setCycle: Reverse
					setMotion: MoveTo 275 120
				)
				(= introDialog (Print 120 2 #mode 1 #at -1 150 #dispose))
				(Timer setReal: self 3)
			)
			(12
				(if modelessDialog (modelessDialog dispose:))
				(= introDialog (Print 120 3 #at -1 150 #dispose))
				(Timer setReal: self 3)
			)
			(13
				(if modelessDialog (modelessDialog dispose:))
				(hat hide:)
				(= kingCloseup
					((Actor new:)
						view: 769
						loop: 0
						posn: 160 114
						cycleSpeed: 5
						cel: 255
						setCycle: EndLoop stopKing
						init:
						yourself:
					)
				)
				(= introDialog (Print 120 4 #at -1 140 #dispose))
				(Timer setReal: self 7)
			)
			(14
				(if modelessDialog (modelessDialog dispose:))
				(kingCloseup dispose:)
				(curRoom drawPic: 201)
				(king
					cycleSpeed: 0
					view: 769
					posn: 135 130
					setLoop: 1
					cel: 255
					setCycle: EndLoop stopKing
					ignoreActors:
					show:
				)
				(rosella
					show:
					view: 759
					setLoop: 1
					xStep: 2
					setCycle: Walk
					setMotion: MoveTo 227 136
				)
				(son
					show:
					view: 760
					loop: 1
					xStep: 2
					setCycle: Walk
					setMotion: MoveTo 216 147 self
				)
				(queen
					show:
					view: 769
					cycleSpeed: 4
					setLoop: 2
					setCycle: EndLoop stopQueen
				)
				((View new:)
					view: 768
					posn: 158 64
					ignoreActors:
					addToPic:
				)
				((View new:)
					view: 769
					loop: 3
					posn: 48 139
					setPri: 0
					ignoreActors:
					addToPic:
				)
				(hat
					show:
					setCel: 3
					setLoop: 0
					posn: 300 144
					stopUpd:
				)
				(= introDialog
					(Print 120 5 #title {King Graham} #at 53 155 #dispose)
				)
			)
			(15
				(if modelessDialog (modelessDialog dispose:))
				(Timer setReal: self 3)
			)
			(16
				(cast eachElementDo: #hide)
				(curRoom drawPic: 203)
				(hat show: setCel: 0 setLoop: 2 posn: 180 99 stopUpd:)
				(= introDialog (Print 120 6 #at -1 152 #dispose))
				(Timer setReal: self 5)
			)
			(17
				(if modelessDialog (modelessDialog dispose:))
				(hat dispose:)
				(curRoom drawPic: 204)
				((View new:)
					view: 752
					loop: 2
					cel: 0
					posn: 123 89
					ignoreActors:
					setPri: 0
					addToPic:
				)
				((View new:)
					view: 752
					loop: 2
					cel: 0
					posn: 204 89
					ignoreActors:
					setPri: 0
					addToPic:
				)
				((Prop new:)
					view: 752
					loop: 4
					posn: 123 63
					cycleSpeed: 0
					setCycle: Forward
					init:
					yourself:
				)
				((Prop new:)
					view: 752
					loop: 4
					posn: 204 63
					cycleSpeed: 0
					setCycle: Reverse
					init:
					yourself:
				)
				((View new:)
					view: 752
					loop: 3
					posn: 47 139
					ignoreActors:
					addToPic:
				)
				(king
					show:
					setPri: 15
					view: 752
					setLoop: 0
					setCel: 0
					posn: 162 144
					stopUpd:
				)
				(queen
					show:
					view: 756
					setPri: 9
					setLoop: 1
					setCel: 0
					ignoreActors:
					posn: 85 177
					stopUpd:
				)
				(son
					show:
					view: 756
					setPri: 9
					loop: 0
					cel: 0
					ignoreActors:
					posn: 228 176
					stopUpd:
				)
				(rosella
					show:
					view: 756
					setPri: 10
					loop: 2
					cel: 0
					posn: 251 177
					stopUpd:
				)
				(= introTimer (Timer setReal: checkHang 15))
				(self cue:)
			)
			(18
				(if (!= (openMusic prevSignal?) -1)
					(-- state)
					(Timer setReal: self 2)
				else
					(Timer setReal: self 5)
					(if (IsObject introTimer) (introTimer dispose:))
				)
			)
			(19
				(cast eachElementDo: #hide)
				(curRoom drawPic: 205)
				(openMusic dispose:)
				((ScriptID INTRO 1) play:)
				(king
					show:
					view: 752
					setLoop: 1
					setCel: 0
					posn: 162 119
					setPri: 8
				)
				(= introDialog (Print 120 7 #at -1 134 #dispose))
				(Timer setReal: self 7)
			)
			(20
				(cls)
				(curRoom drawPic: 204)
				(cast eachElementDo: #show)
				((View new:)
					view: 752
					loop: 3
					posn: 47 139
					ignoreActors:
					addToPic:
				)
				((View new:)
					view: 752
					loop: 2
					cel: 0
					posn: 123 89
					ignoreActors:
					setPri: 0
					addToPic:
				)
				((View new:)
					view: 752
					loop: 2
					cel: 0
					posn: 204 89
					ignoreActors:
					setPri: 0
					addToPic:
				)
				(king
					view: 752
					setPri: 15
					setLoop: 0
					setCel: 0
					posn: 163 137
					stopUpd:
				)
				(queen setCel: 255 stopUpd:)
				(son setCel: 255 stopUpd:)
				(rosella
					setStep: 4 -1
					setCycle: EndLoop
					setMotion: MoveTo 340 176
				)
				(= introDialog (Print 120 8 #at -1 10 #width 300 #dispose))
				(Timer setReal: self 4)
			)
			(21
				(= inCutscene TRUE)
				(if modelessDialog (modelessDialog dispose:))
				(curRoom newRoom: 221)
			)
		)
	)
)

(instance stopKing of Script
	(properties)
	
	(method (cue)
		(king stopUpd:)
	)
)

(instance stopQueen of Script
	(properties)
	
	(method (cue)
		(queen stopUpd:)
	)
)

(instance stopSon of Script
	(properties)
	
	(method (cue)
		(son stopUpd:)
	)
)

(instance stopRosella of Script
	(properties)
	
	(method (cue)
		(rosella stopUpd:)
	)
)

(instance checkHang of Script
	(properties)
	
	(method (cue)
		(timers eachElementDo: #dispose 84)
		(introSc changeState: 19)
	)
)
