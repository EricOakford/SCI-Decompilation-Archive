;;; Sierra Script 1.0 - (do not remove this comment)
(script# 104)
(include game.sh)
(use Main)
(use Intrface)
(use Sound)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	rm104 0
	proc104_1 1
	proc104_2 2
)

(local
	neonSign
	cameraScreen
	hostOnTv
	host
	wheel
	cameraMan
	lana
	applauseSignal
)
(procedure (proc104_1)
	(hostOnTv
		setLoop: 7
		setCel: 0
		cycleSpeed: 0
		setCycle: Forward
	)
	(host setCel: 0 cycleSpeed: 0 setCycle: Forward)
)

(procedure (proc104_2)
	(host setCel: 0 stopUpd:)
	(hostOnTv setCel: 0 stopUpd:)
)

(instance mDrumRoll of Sound
	(properties
		number 9
		priority 100
		loop -1
	)
)

(instance mThemeSong of Sound
	(properties
		number 109
		priority 100
		loop -1
	)
)

(instance rm104 of Room
	(properties
		picture 104
		horizon 1
	)
	
	(method (init)
		(Load VIEW 207)
		(Load VIEW 215)
		(Load VIEW 212)
		(Load VIEW 213)
		(Load VIEW 214)
		(Load VIEW 216)
		(Load SOUND 109)
		(Load SOUND 9)
		(super init:)
		(mDrumRoll init:)
		(mThemeSong init:)
		((= applauseSignal (Prop new:))
			view: 207
			setLoop: 5
			setCel: 0
			setPri: 15
			posn: 156 20
			init:
			stopUpd:
		)
		((= neonSign (Prop new:))
			view: 214
			loop: 0
			cel: 0
			posn: 163 54
			priority: 2
			init:
			stopUpd:
		)
		((View new:)
			view: 214
			loop: 1
			cel: 0
			posn: 166 77
			setPri: 2
			ignoreActors:
			addToPic:
		)
		((= host (Prop new:))
			view: 207
			loop: 1
			cel: 0
			posn: 164 78
			setPri: 5
			ignoreActors:
			setCycle: Forward
			init:
		)
		((View new:)
			view: 207
			loop: 0
			cel: 0
			posn: 164 93
			setPri: 5
			ignoreActors:
			addToPic:
		)
		((= cameraScreen (Prop new:))
			view: 216
			loop: 0
			posn: 276 5
			setPri: 15
			ignoreActors:
			isExtra: 1
			setCycle: Forward
			init:
		)
		((= hostOnTv (Prop new:))
			view: 216
			loop: 7
			cel: 0
			posn: 49 51
			setPri: 15
			ignoreActors:
			setCycle: Forward
			init:
		)
		((= wheel (Prop new:))
			view: 213
			loop: 0
			cel: 0
			posn: 250 96
			setPri: 9
			ignoreActors:
			stopUpd:
			init:
		)
		((= cameraMan (Actor new:))
			view: 212
			loop: 1
			posn: 90 131
			moveSpeed: 1
			cycleSpeed: 1
			init:
		)
		((= lana (Actor new:))
			name: {lana}
			view: 215
			loop: 0
			posn: 19 1091
			ignoreActors:
			setCycle: Walk
			illegalBits: 0
			init:
		)
		(ego
			view: 100
			setLoop: -1
			posn: 33 91
			setCycle: Walk
			init:
			illegalBits: 0
		)
		(HandsOff)
		(= currentStatus egoOnTVShow)
		(self setScript: rm104Script)
	)
)

(instance rm104Script of Script
	(properties)
	
	(method (doit)
		(super doit:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= seconds 2))
			(1
				(Print 104 0)
				(Print 104 1)
				(applauseSignal setCycle: Forward)
				(= seconds 1)
			)
			(2
				(Print 104 2 #at -1 152)
				(proc104_2)
				(ego setMotion: MoveTo 77 92 self)
			)
			(3
				(proc104_1)
				(applauseSignal stopUpd:)
				(ego setMotion: MoveTo 99 92 self)
			)
			(4
				(Print 104 3)
				(proc104_2)
				(host setLoop: 3 cel: 0 setCycle: EndLoop self)
			)
			(5
				(ego setMotion: MoveTo 144 98 self)
			)
			(6
				(Print 104 4)
				(cameraMan setMotion: MoveTo 222 132 setCycle: Walk)
				(ego setMotion: MoveTo 234 101 self)
				(= cycles 30)
			)
			(7
				(host setLoop: 1)
				(proc104_2)
			)
			(8
				(mDrumRoll play:)
				(= seconds 2)
			)
			(9
				(Print 104 5)
				(cameraMan stopUpd:)
				(ego
					view: 213
					loop: 1
					cel: 0
					cycleSpeed: 1
					setCycle: EndLoop self
				)
				(wheel cel: 0 cycleSpeed: 0 setCycle: Forward)
				(hostOnTv setLoop: 2 cel: 0 cycleSpeed: 1 setCycle: Forward)
			)
			(10
				(ego
					view: 100
					loop: 0
					cel: 0
					cycleSpeed: 0
					setMotion: 0
					stopUpd:
				)
				(hostOnTv setLoop: 3 cel: 0 cycleSpeed: 0)
				(= seconds 3)
			)
			(11
				(wheel cycleSpeed: 1)
				(hostOnTv cycleSpeed: 1)
				(= seconds 2)
			)
			(12
				(hostOnTv setLoop: 4 cycleSpeed: 2)
				(wheel cycleSpeed: 2)
				(= seconds 2)
			)
			(13
				(wheel cycleSpeed: 4)
				(hostOnTv cycleSpeed: 4)
				(= seconds 2)
			)
			(14
				(hostOnTv setLoop: 1 cel: 0 cycleSpeed: 1 setCycle: EndLoop)
				(wheel stopUpd:)
				(mDrumRoll dispose:)
				(mThemeSong play:)
				(neonSign setCycle: Forward)
				(applauseSignal setCycle: Forward)
				(= seconds 4)
			)
			(15
				(proc104_1)
				(theGame changeScore: 12)
				(= seconds 1)
			)
			(16
				(Print 104 6)
				(Print 104 7 #at -1 152)
				(Print 104 8 #at -1 152)
				(= seconds 3)
			)
			(17
				(Print 104 9)
				(ego setCycle: Walk setMotion: MoveTo 149 98 self)
				(proc104_2)
				(hostOnTv setLoop: 5 setCycle: Forward)
				(cameraMan setMotion: MoveTo 161 132)
			)
			(18
				(applauseSignal stopUpd:)
				(neonSign stopUpd:)
				(cameraMan stopUpd:)
				(ego setMotion: MoveTo 149 95)
				(hostOnTv setLoop: 6 stopUpd:)
				(host setLoop: 4 cel: 0 setCycle: EndLoop self)
				(Print 104 10 #draw)
			)
			(19
				(host setLoop: 1 forceUpd:)
				(proc104_2)
				(lana posn: 19 91 setMotion: MoveTo 82 91 self)
			)
			(20
				(lana setMotion: MoveTo 117 94 self)
				(ego loop: 1 stopUpd:)
			)
			(21
				(lana setLoop: 1 setCel: 0 setCycle: 0)
				(ego setCycle: 0)
				(cameraMan setMotion: MoveTo 161 132)
				(= seconds 2)
			)
			(22
				(Print 104 11)
				(lana setCel: 0 cycleSpeed: 1 setCycle: EndLoop)
				(ego
					view: 215
					setLoop: 2
					setCel: 0
					cycleSpeed: 1
					setCycle: EndLoop self
				)
			)
			(23
				(lana stopUpd:)
				(theGame changeScore: 7)
				(ego get: 4)
				(= seconds 3)
			)
			(24
				(Print 104 12)
				(ego hide:)
				(host setLoop: 2 cycleSpeed: 1 setCycle: Forward)
				(= seconds 4)
			)
			(25
				(host setLoop: 1)
				(ego view: 100 loop: 0 show:)
				(proc104_1)
				(= seconds 3)
			)
			(26
				(proc104_2)
				(Print 104 13 #draw)
				(Print 104 14 #at -1 152)
				(hostOnTv setLoop: 5 setCycle: Forward)
				(= seconds 3)
			)
			(27
				(Print 104 15)
				(Print 104 16)
				(hostOnTv setLoop: 6 setCycle: Forward)
				(neonSign setCycle: Forward)
				(= seconds 5)
			)
			(28
				(mThemeSong stop:)
				(neonSign stopUpd:)
				(hostOnTv setLoop: 8)
				(Print 104 17 #draw)
				(Print 104 18)
				(= seconds 2)
			)
			(29
				(ego
					cycleSpeed: 0
					setLoop: -1
					setCycle: Walk
					setMotion: MoveTo 18 112 self
				)
			)
			(30
				(ego setMotion: MoveTo -12 112 self)
			)
			(31
				(Print 104 19)
				(= seconds 2)
			)
			(32
				(Print 104 20 #at -1 152)
				(curRoom newRoom: 101)
			)
		)
	)
	
	(method (handleEvent)
	)
)
