;;; Sierra Script 1.0 - (do not remove this comment)
(script# 200)
(include game.sh)
(use Main)
(use Intrface)
(use subMarine)
(use EgoDead)
(use Approach)
(use Avoider)
(use Motion)
(use User)
(use System)

(public
	guysWalkInScript 0
)

(local
	readMap
	readOrder
	[str 3]
	askCount
)
(instance guysWalkInScript of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego view: 232 posn: 310 120 setCycle: Walk loop: 1 init:)
				((ScriptID 30 2)
					view: 133
					posn: 300 122
					init:
					setCycle: Walk
					loop: 1
					setAvoider: Avoider
				)
				((ScriptID 30 3) setCycle: EndLoop self)
			)
			(1
				((ScriptID 30 2) setMotion: MoveTo 280 122 self)
			)
			(2
				((ScriptID 30 2) setMotion: MoveTo 270 122 self)
			)
			(3
				((ScriptID 30 3) setCycle: BegLoop)
				(ego setMotion: MoveTo 270 122)
				((ScriptID 30 2) setMotion: MoveTo 222 115 self)
				(Print 200 0)
			)
			(4
				((ScriptID 30 4) illegalBits: 0 init:)
				((ScriptID 30 5) setCycle: EndLoop self)
			)
			(5
				((ScriptID 30 5) stopUpd:)
				((ScriptID 30 2) setMotion: MoveTo 198 115 self)
			)
			(6
				((ScriptID 30 2) loop: 3)
				(ego setMotion: MoveTo 213 130 self)
			)
			(7
				((ScriptID 30 4) hide:)
				(ego setMotion: MoveTo 213 152 self)
				((ScriptID 30 2)
					view: 134
					setLoop: 0
					setMotion: MoveTo 213 130
				)
			)
			(8
				(ego heading: 90)
				((ego looper?) doit: ego (ego heading?))
				((ScriptID 30 2) setMotion: MoveTo 213 147 self)
			)
			(9
				((ScriptID 30 2) view: 133 loop: 0)
				(= cycles 2)
			)
			(10
				((ScriptID 30 4)
					view: 134
					setLoop: 2
					setCel: 0
					posn: 227 131
					setPri: 11
					show:
					setMotion: MoveTo 237 131 self
				)
			)
			(11
				(client setScript: tryToOpenCaseScript)
			)
		)
	)
)

(instance tryToOpenCaseScript of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(switch register
					(0
						(Print 200 1
							#edit @str 12
						)
						(cond 
							((not (StrCmp @str {134}))
								(client setScript: openCaseScript)
							)
							((not (StrCmp @str {}))
								(self init:)
							)
							(else (++ register)
								(self init:)
							)
						)
					)
					(1
						(Print 200 2 #edit @str 12)
						(cond 
							((not (StrCmp @str {134}))
								(client setScript: openCaseScript)
							)
							((not (StrCmp @str {}))
								(self init:)
							)
							(else (++ register)
								(self init:)
							)
						)
					)
					(2
						(Print 200 3 #edit @str 12)
						(cond 
							((not (StrCmp @str {134}))
								(client setScript: openCaseScript)
							)
							((not (StrCmp @str {}))
								(self init:)
							)
							(else
								(++ register)
								(self init:)
							)
						)
					)
					(3
						(Print 200 4)
						(Print 200 5)
						(EgoDead 7 0 0 200 6)
					)
				)
			)
		)
	)
)

(instance openCaseScript of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				((ScriptID 30 4) setCycle: EndLoop self)
				(theGame changeScore: 1)
			)
			(1
				((ScriptID 30 6) setPri: 14 init: stopUpd:)
				((ScriptID 30 7) setPri: 13 init: stopUpd:)
				((ScriptID 30 8) setPri: 15 init:)
				(User canInput: 1)
			)
			(2
				(HandsOff)
				(Print 200 7)
				(Print 200 8)
				(Print 200 9)
				((ScriptID 30 4) setCycle: BegLoop self)
				(ego put: 0)
			)
			(3
				((ScriptID 30 7) dispose:)
				((ScriptID 30 6) dispose:)
				((ScriptID 30 4) setMotion: MoveTo 227 131 self)
			)
			(4
				((ScriptID 30 4) hide:)
				((ScriptID 30 2)
					view: 134
					setLoop: 1
					setMotion: MoveTo 213 130 self
				)
			)
			(5
				((ScriptID 30 2) setMotion: MoveTo 198 115 self)
			)
			(6
				((ScriptID 30 4)
					view: 430
					loop: 0
					cel: 3
					setPri: -1
					posn: 195 87
					show:
					stopUpd:
				)
				((ScriptID 30 2)
					view: 133
					loop: 3
					setLoop: -1
					setMotion: MoveTo 222 115 self
				)
			)
			(7
				((ScriptID 30 5) setCycle: BegLoop self)
			)
			(8
				((ScriptID 30 5) stopUpd:)
				(Print 200 10)
				(Print 200 11)
				((ScriptID 30 2)
					setAvoider: Avoider
					setMotion: Approach (ScriptID 30 3) 12 self
				)
			)
			(9
				((ScriptID 30 3) setCycle: EndLoop self)
			)
			(10
				((ScriptID 30 2)
					illegalBits: 0
					setMotion: MoveTo
						(+ ((ScriptID 30 3) x?) 10)
						((ScriptID 30 2) y?)
						self
				)
			)
			(11
				((ScriptID 30 2)
					setMotion: MoveTo (+ ((ScriptID 30 2) x?) 10) ((ScriptID 30 2) y?)
				)
				((ScriptID 30 3) setCycle: BegLoop self)
			)
			(12
				(HandsOn)
				(ego observeControl: cBLUE)
				(subMarine cue: 1)
				((ScriptID 30 2) setScript: (ScriptID 30 9))
				(self dispose:)
				(DisposeScript 200)
			)
		)
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((or (Said 'ask//combination') (Said 'get/combination'))
				(switch askCount
					(0
						(Print 200 12)
						(theGame changeScore: 5)
						(++ askCount)
					)
					(1
						(Print 200 13)
						(++ askCount)
					)
					(2
						(Print 200 14)
						(++ askCount)
					)
					(else
						(Print 200 15)
					)
				)
			)
			((not ((ScriptID 30 5) cel?)))
			(
				(or
					(Said 'open/top')
					(Said 'adjust,insert,use/id,card[<id][/slot,hole]')
				)
				(if ((ScriptID 30 4) cel?)
					(Print 200 16)
				else
					(Print 200 17)
				)
			)
			((Said 'get/envelope[<manila]')
				(if (ego has: iEnvelope)
					(AlreadyTook)
				else
					(Print 200 18)
					(ego get: iEnvelope)
					((ScriptID 30 4) loop: 3)
					((ScriptID 30 8) dispose:)
				)
			)
			((Said 'look/envelope[<manila]')
				(if (not (ego has: iEnvelope))
					(DontHave)
				else
					(Print 200 19)
				)
			)
			((Said 'open/envelope[<manila]')
				(if (not (ego has: iEnvelope))
					(DontHave)
				else
					(Print 200 20)
				)
			)
			((Said 'look,read[<at]/chart,map')
				(cond 
					((not (ego has: iEnvelope))
						(DontHave)
						(return)
					)
					(readMap
						(Print 200 21 #icon 430 5 1)
					)
					(else
						(Print 200 22
							#icon 430 5 1
						)
						(= readMap TRUE)
					)
				)
				(if readOrder
					(self cue:)
				)
			)
			((Said 'get/order')
				(if (ego has: iEnvelope)
					(Print 200 23)
				else
					(DontHave)
				)
			)
			((Said 'look,read[<at]/order')
				(cond 
					(readOrder
						(Print 200 24)
					)
					((ego has: iEnvelope)
						(Print 200 25)
						(Print 200 26)
						(Print 200 27)
						(Print 200 28)
						(Print 200 29)
						(Print 200 30)
						(Print 200 31)
						(Print 200 32)
						(Print 200 33)
						(Print 200 34)
						(Print 200 35)
						(Print 200 36)
						(Print 200 37)
						(if readMap
							(self cue:)
						else
							(= seconds 6)
						)
						(= readOrder TRUE)
					)
					(else
						(DontHave)
					)
				)
			)
			((Said 'close/briefcase')
				(if ((ScriptID 30 4) cel?)
					(DontNeedTo)
				else
					(Print 200 17)
				)
			)
		)
	)
)
