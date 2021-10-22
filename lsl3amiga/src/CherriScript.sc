;;; Sierra Script 1.0 - (do not remove this comment)
(script# 422)
(include game.sh)
(use Main)
(use n021)
(use Intrface)
(use Follow)
(use Motion)
(use System)

(public
	CherriScript 0
)
(synonyms
	(babe babe dale cheri)
)

(instance CherriScript of Script
	(method (doit)
		(super doit:)
		(if (and (== state 10) (> (ego x?) 280))
			(self cue:)
		)
		(if
			(and
				(== showroomState SRcherriOnPhone)
				(== (client loop?) 4)
				(== (client x?) 82)
				(== (client y?) 124)
			)
			(switch (Random 0 6)
				(0 (client setCel: 0))
				(1 (client setCycle: Forward))
			)
		)
	)
	
	(method (changeState newState)
		(ChangeScriptState self newState 3 2)
		(switch (= state newState)
			(0
				(if (== showroomState SRknowsAboutDeed)
					(self changeState: 3)
				)
				(if (== showroomState SRshowDone)
					(self changeState: 10)
					(client posn: -20 143 stopUpd:)
				)
			)
			(1
				(Ok)
				(HandsOff)
				(= currentStatus 14)
				(Printf 422 5 introductoryPhrase)
				(client setStep: 0 0 setMotion: Follow ego 222)
				(= seconds 3)
			)
			(2
				(if (not (Btst fMetCherri))
					(Bset fMetCherri)
					(theGame changeScore: 5)
				)
				(Print 422 6)
				(= saveEgoX (ego x?))
				(= saveEgoY (ego y?))
				(= saveEgoLoop (ego loop?))
				(curRoom newRoom: 435)
			)
			(3
				(HandsOff)
				(= seconds 3)
			)
			(4
				(Print 422 7)
				(= seconds 3)
			)
			(5
				(Print 422 8)
				(client
					illegalBits: 0
					ignoreActors: FALSE
					setLoop: 1
					setCycle: Walk
					setMotion: MoveTo 45 140 self
				)
				(if
					(and
						(> (ego y?) (client y?))
						(< (ego x?) (+ (client x?) 15))
					)
					(ego setCycle: Walk setMotion: MoveTo 97 (ego y?))
				)
			)
			(6
				(theDoor setCycle: EndLoop self)
			)
			(7
				(Print 422 9)
				(theDoor stopUpd:)
				(client setMotion: MoveTo -20 140 self)
			)
			(8
				(theDoor setCycle: BegLoop self)
			)
			(9
				(soundFX number: 11 loop: 1 play:)
				(= showroomState SRcherriBackstage)
				(theDoor stopUpd:)
				(NormalEgo)
				(ego observeControl: cYELLOW cLMAGENTA)
				(client dispose:)
				(self dispose:)
			)
			(10
				(= seconds 15)
			)
			(11
				(if (< (ego x?) 160)
					(-- state)
					(= cycles 2)
				else
					(HandsOff)
					(theDoor setCycle: EndLoop self)
					(= seconds 0)
				)
			)
			(12
				(theDoor stopUpd:)
				(client
					posn: 13 140
					loop: 0
					illegalBits: 0
					setCycle: Walk
					setMotion: MoveTo 45 140 self
				)
			)
			(13
				(theDoor setCycle: BegLoop self)
			)
			(14
				(soundFX number: 11 loop: 1 play:)
				(theDoor stopUpd:)
				(client setMotion: MoveTo 82 124 self)
			)
			(15
				(client loop: 4)
				(= showroomState 2)
				(HandsOn)
				(ego observeControl: cYELLOW cLMAGENTA)
			)
		)
	)
	
	(method (handleEvent event)
		(if
			(or
				(!= (event type?) saidEvent)
				(!= showroomState SRcherriOnPhone)
				(event claimed?)
			)
			(return)
		)
		(cond 
			((or (Said 'give/babe') (Said 'give/anyword/babe'))
				(Print 422 0)
			)
			((Said 'look/babe')
				(cond 
					((!= showroomState SRcherriOnPhone)
						(Print 422 1)
					)
					((!= currentStatus egoNORMAL)
						(GoodIdea)
					)
					((!= (client xLast?) (client x?))
						(Print 422 2)
					)
					((not (& (ego onControl:) cMAGENTA))
						(Print 422 3)
					)
					(else
						(self changeState: 1)
					)
				)
			)
			((and (== showroomState SRcherriOnPhone) (Said '/babe'))
				(Print 422 4)
			)
		)
	)
)
