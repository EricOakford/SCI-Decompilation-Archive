;;; Sierra Script 1.0 - (do not remove this comment)
(script# TOWN)
(include game.sh)
(use Main)
(use Intrface)
(use LoadMany)
(use Save)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	town 0
)

(local
	townGate
	egoHeadTurn
	ottoBody
	sheriffBody
	pipeSmoke
	puffSmoke
	yoYoState
)
(instance town of Room
	(properties
		picture rTownOutlook
		style IRISIN
	)
	
	(method (init)
		(LoadMany VIEW rTownOutlook vOtto)
		(Load SOUND sTown)
		(super init:)
		(= yoYoState 1)
		(music number: sTown play:)
		((= townGate (View new:))
			posn: 114 173
			cel: 1
		)
		(townGate
			view: rTownOutlook
			loop: 1
			setPri: 15
			init:
			stopUpd:
			ignoreActors:
		)
		(sheriff init:)
		((View new:)
			view: rTownOutlook
			loop: 0
			posn: 71 122
			init:
		)
		((View new:)
			view: rTownOutlook
			posn: 58 69
			loop: 2
			cel: 0
			setPri: 14
			init:
			stopUpd:
			addToPic:
		)
		((View new:)
			view: rTownOutlook
			posn: 259 58
			loop: 2
			cel: 1
			setPri: 14
			init:
			stopUpd:
			addToPic:
		)
		((View new:)
			view: rTownOutlook
			posn: 173 72
			loop: 2
			cel: 2
			setPri: 14
			init:
			stopUpd:
			addToPic:
		)
		((View new:)
			view: rTownOutlook
			posn: 271 104
			loop: 2
			cel: 3
			setPri: 8
			init:
			stopUpd:
			addToPic:
		)
		(= roomTimer 125)
		(ego
			illegalBits: cWHITE
			posn: 205 235
			init:
		)
		(self setScript: entranceScript)
	)
	
	(method (doit)
		(super doit:)
		(if (> roomTimer 1)
			(-- roomTimer)
		)
		(if (== roomTimer 1)
			(= roomTimer 0)
			(curRoom newRoom: MAGIC)
		)
	)
	
	(method (dispose)
		(super dispose:)
	)
)

(instance entranceScript of Script
	
	(method (changeState newState &tmp [temp0 100])
		(switch (= state newState)
			(0
				(= cycles 5)
			)
			(1
				(Print 3 0
					#at -1 10
					#width 300
					#dispose
					#window aTalk
				)
				(ego setMotion: MoveTo 187 187 self)
			)
			(2
				((= egoHeadTurn (View new:))
					view: rTownOutlook
					loop: 8
					cel: 0
					posn: 187 154
					setPri: 15
					ignoreActors:
					init:
				)
				(= cycles 10)
			)
			(3
				(egoHeadTurn setCel: 1 posn: 188 154)
				(= cycles 10)
			)
			(4
				(egoHeadTurn dispose:)
				(ego setMotion: MoveTo 187 177 self)
			)
			(5
				(ego setMotion: MoveTo 157 177 self)
			)
			(6
				(ego setMotion: MoveTo 20 143 self)
				(if modelessDialog
					(modelessDialog dispose:)
				)
			)
			(7
				(ego setMotion: MoveTo -20 123 self)
			)
		)
	)
)

(instance sheriff of Actor
	(properties
		view rTownOutlook
		loop 3
	)
	
	(method (init)
		(self illegalBits: 0)
		((= pipeSmoke (Prop new:))
			view: rTownOutlook
			loop: 5
			cel: 0
			posn: 159 102
			setPri: 9
			init:
			ignoreActors:
			setCycle: Forward
			cycleSpeed: 1
			stopUpd:
		)
		((= puffSmoke (Prop new:))
			view: rTownOutlook
			loop: 7
			cel: 0
			posn: 156 99
			setPri: 9
			init:
			ignoreActors:
			cycleSpeed: 1
			stopUpd:
		)
		((= sheriffBody (View new:))
			view: rTownOutlook
			loop: 6
			cel: 0
			posn: 152 128
			init:
			ignoreActors:
			stopUpd:
		)
		((= ottoBody (Prop new:))
			view: vOtto
			loop: 1
			posn: 194 76
			init:
			cycleSpeed: 1
		)
		((View new:)
			view: vOtto
			posn: 195 118
			loop: 0
			cel: 0
			init:
			addToPic:
		)
		(ottoBody setScript: YoYoScript)
		(self setScript: sheriffScript)
		(super init:)
	)
)

(instance sheriffScript of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(sheriffBody hide: stopUpd:)
				(pipeSmoke hide: stopUpd:)
				(puffSmoke hide: stopUpd:)
				(sheriff
					posn: 152 128
					startUpd:
					cycleSpeed: 2
					show:
					setCycle: EndLoop self
				)
			)
			(1 (sheriff setCycle: BegLoop self))
			(2
				(sheriff hide: stopUpd:)
				(sheriffBody show: stopUpd:)
				(puffSmoke show: startUpd: setCycle: EndLoop)
				(pipeSmoke show: startUpd:)
				(= cycles (Random 30 70))
			)
			(3 (self changeState: 0))
		)
	)
)

(instance YoYoScript of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(cond 
					((== (mod yoYoState 5) 0) (self changeState: 2))
					((== (mod yoYoState 8) 0) (self changeState: 4))
					(else
						(++ yoYoState)
						(ottoBody loop: 1 cel: 0 setCycle: EndLoop)
						(= cycles 20)
					)
				)
			)
			(1 (self changeState: 0))
			(2
				(++ yoYoState)
				(ottoBody loop: 2 cel: 0 setCycle: EndLoop)
				(= cycles 30)
			)
			(3 (self changeState: 0))
			(4
				(= yoYoState 1)
				(ottoBody loop: 3 cel: 0 setCycle: EndLoop self)
			)
			(5 (= cycles 30))
			(6 (self changeState: 0))
		)
	)
)

(instance aTalk of SysWindow
	(properties
		color vBLUE
		back vLCYAN
	)
)
