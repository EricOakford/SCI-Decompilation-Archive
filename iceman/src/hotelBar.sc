;;; Sierra Script 1.0 - (do not remove this comment)
(script# 11)
(include game.sh)
(use Main)
(use Intrface)
(use tahiti)
(use EgoDead)
(use ChangeScriptState)
(use InitAllFeatures)
(use SolvePuzzle)
(use ForCount)
(use LoadMany)
(use QScript)
(use Follow)
(use RFeature)
(use Sight)
(use Extra)
(use Sound)
(use Motion)
(use Game)
(use User)
(use Actor)
(use System)

(public
	hotelBar 0
	lush 1
	proc11_2 2
	lSV 3
)
(synonyms
	(drink champagne)
)

(local
	local0
	local1
	agentHuff
	local3 =  1
	local4
)
(procedure (proc11_2 param1)
	(cast eachElementDo: #perform eCC param1)
)

(procedure (localproc_304e param1 param2 param3 param4 param5)
	(Print
		param1
		(switch argc
			(3 (Random param2 param3))
			(4
				(+
					param2
					(Random 0 (- (/ (+ 1 (- param3 param2)) param4) 1))
				)
			)
			(5
				(+ param2 (Random 0 (- param4 1)) (* param4 param5))
			)
			(else  param2)
		)
	)
)

(instance hotelBar of Room
	(properties
		picture 11
		west 10
		picAngle 70
	)
	
	(method (init)
		(ScriptID 962)
		(ScriptID 822)
		Follow
		ForwardCounter
		(Load SCRIPT 359)
		(Load SCRIPT 358)
		(LoadMany TEXT 611 612)
		(LoadMany VIEW 206 207 411 212 11 211 511 611 611 811)
		(LoadMany SOUND 2 6 8)
		(tahiti flags: (& (tahiti flags?) $ffef))
		(lAB init:)
		(bartender init:)
		(if (not (& (tahiti flags?) $0040))
			((= agentHuff (ScriptID 309 0)) init:)
			(if (& (tahiti flags?) $0004)
				(cV addToPic:)
				(aCup init:)
			)
		)
		(wife init:)
		(husband init:)
		(mAB init:)
		(lABTE init:)
		((Clone lABTE) loop: 9 x: 201 y: 108 init:)
		(sV init:)
		(ego illegalBits: 0 view: 206 init:)
		(if (== prevRoomNum 199)
			(agentHuff
				view: 212
				setCycle: Walk
				xStep: 3
				yStep: 2
				illegalBits: 0
				posn: 140 157
				setScript: aSS
			)
			(ego posn: 126 152 loop: 0 xStep: 3 yStep: 2)
		else
			(if (not (& (tahiti flags?) $0040))
				(agentHuff
					view: 211
					loop: 1
					setCel: 16
					setPri: 15
					ignoreActors: 1
					stopUpd:
					illegalBits: 0
					posn: 288 169 15
					setScript: aS 0 (& (tahiti flags?) $0004)
				)
			)
			(ego posn: 10 115)
		)
		(ego observeControl: cWHITE)
		(super init:)
		(addToPics
			add:
				pP
				tP
				cP
				lABAP
				mAB
				hCPV
				mFF
				mIBF
				cPPV
				eChair
				((Clone cPPV) x: 200 y: 155 z: 23 priority: 11 yourself:)
				((Clone cPPV) x: 264 y: 155 priority: 15 yourself:)
			eachElementDo: #init
			doit:
		)
		(barSongScript start: (tahiti whichSong?))
		(self
			setRegions: 300
			setScript: barSongScript
			setFeatures: wF lABF mirror
		)
		(InitAllFeatures)
		(if (not (& (tahiti flags?) $0002))
			(lush init:)
			(lAV init:)
		else
			(lSV init:)
		)
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((Said 'sit')
				(cond 
					((== (ego view?) 206)
						(Print 11 0)
					)
					((OneOf (ego view?) 511 11)
						(Print 11 1)
					)
					(else
						(event claimed: FALSE)
					)
				)
			)
			((Said 'stand')
				(if (== (ego view?) 206)
					(Print 11 2)
				else
					(NotNow)
				)
			)
			((Said 'drink')
				(if (== (ego view?) 206)
					(Print 11 3)
				else
					(Print 11 4)
				)
			)
			((Said 'look[<at,around][/room]')
				(Print 11 5)
			)
			((Said 'dance')
				(Print 11 6)
			)
			((Said 'open/door')
				(Print 11 7)
			)
			((Said 'ask/babe/date')
				(Print 11 8)
			)
			((Said 'buy,order')
				(NotClose)
			)
		)
	)
)

(instance barSongScript of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(tahiti whichSong: state)
				(barSong number: (SoundFX 6) play: self)
			)
			(1
				(tahiti whichSong: state)
				(barSong number: (SoundFX 2) play: self)
			)
			(2
				(tahiti whichSong: state)
				(barSong number: 8 play: self)
			)
			(3
				(tahiti whichSong: state)
				(self init:)
			)
		)
	)
)

(instance barSong of Sound
	(properties
		number 6
	)
)

(instance husband of Actor
	(properties
		y 110
		x -20
		view 211
		loop 3
	)
	
	(method (init)
		(super init:)
		(if (& (tahiti flags?) $0001)
			(hS start: 5)
			(self
				view: 11
				setLoop: 5
				posn: 169 146
				setCel: 16
				setScript: hS
			)
		else
			(self setLoop: loop setPri: 9)
		)
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((IsOffScreen self))
			((Said 'look[<at][/man]')
				(Print 11 9)
			)
			((Said 'address[/man]')
				(localproc_304e 611 84 91)
			)
		)
	)
)

(instance hS of Script

	(method (changeState newState)
		(switch (= state newState)
			(0 (= seconds 10))
			(1
				(tahiti flags: (| (tahiti flags?) $0001))
				(client
					setLoop: 3
					setCycle: Walk
					setMotion: MoveTo 126 120 self
				)
			)
			(2
				(if
					(and
						(< (ego distanceTo: wife) 50)
						(not (ego script?))
						(not (& (tahiti flags?) $0010))
						(not (agentHuffScript client?))
						(== (curRoom curPic?) 11)
					)
					(tahiti flags: (| (tahiti flags?) $0010))
					(client setScript: (ScriptID 358 0) 0 1)
				else
					(= cycles 1)
				)
			)
			(3
				(client illegalBits: 0 setMotion: MoveTo 169 146 self)
			)
			(4
				(client
					view: 11
					cel: 0
					setPri: 10
					setLoop: 5
					setCycle: EndLoop self
				)
			)
			(5
				(client stopUpd:)
				(proc11_2 1)
			)
			(6
				(if (not (& (tahiti flags?) $0010))
					(HandsOff)
					(client
						setPri: (- (hCPV priority?) 1)
						setCycle: BegLoop self
					)
				else
					(= start 5)
					(self init:)
				)
			)
			(7
				(client setScript: (ScriptID 358 0) 0 0)
				(tahiti flags: (| (tahiti flags?) $0010))
			)
		)
	)
)

(instance eChair of PicView
	(properties
		y 171
		x 231
		z 1
		view 11
		loop 6
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((Said 'sit')
				(cond 
					((> (ego distanceTo: self) 30)
						(event claimed: FALSE)
					)
					((not (cast contains: agentHuff))
						(Print 11 10)
					)
					((not (& (tahiti flags?) $0800))
						(Print 11 11)
					)
					((not (& (tahiti flags?) $1000))
						(Print 11 12)
					)
					(else
						(ego setScript: eSitAS)
					)
				)
			)
		)
	)
)

(instance aS of Script

	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			(
				(and
					(> (ego distanceTo: client) 55)
					(!= (ego script?) eSitAS)
				)
			)
			((CantBeSeen client))
			((Said 'kiss,fuck,suck[/babe,boob,cunt,bitch,stacy]')
				(localproc_304e 611 30 33)
				(agentHuff setScript: agentHuffScript)
			)
			(
				(or
					(Said 'buy,order/drink,(tai<mai)/babe,stacy')
					(Said 'buy,order/drink,(tai<mai)<babe,stacy')
				)
				(cond 
					((& (tahiti flags?) $0800) (Print 11 13))
					((ego script?)
						(Print 11 14)
					)
					(else
						(tahiti flags: (| (tahiti flags?) $0800))
						(ego setScript: gADS)
					)
				)
			)
			(
			(or (Said 'dance') (Said 'ask/babe,stacy/dance'))
				(ego setScript: wTDS self)
				(if (& (tahiti flags?) $0800) (aCup init:))
			)
			((Said 'address')
				(cond 
					((not (& (tahiti flags?) $2000))
						(localproc_304e 611 16 20)
						(tahiti flags: (| (tahiti flags?) $2000))
					)
					((& (tahiti flags?) $1000)
						(localproc_304e 611 25 29)
					)
					(else
						(localproc_304e 611 21 24)
					)
				)
			)
		)
	)
)

(instance agentHuffScript of Script
	
	(method (doit)
		(super doit:)
		(if (> state 1)
			(ego
				heading: (GetAngle (ego x?) (ego y?) (agentHuff x?) (agentHuff y?))
			)
			((ego looper?) doit: ego (ego heading?))
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(proc11_2 0)
				(agentHuff
					setPri: (- (agentHuff priority?) 1)
					setLoop: 1
					setCycle: BegLoop self
				)
				(tahiti flags: (| (tahiti flags?) $0040))
				(cond 
					((and (== (ego view?) 11) (== (ego loop?) 7))
						(ego setScript: eSFAS self 0)
					)
					((ego inRect: 248 129 293 167)
						(ego setMotion: MoveTo 248 157 self)
					)
					(else (= cycles 1))
				)
			)
			(1)
			(2
				(HandsOff)
				(agentHuff
					view: 212
					setCycle: Walk
					setLoop: -1
					z: 0
					setPri: 12
					setMotion: MoveTo (agentHuff x?) (- (agentHuff y?) 10) self
				)
			)
			(3
				(agentHuff setMotion: MoveTo 238 129 self setPri: -1)
			)
			(4
				(agentHuff setMotion: MoveTo 130 121 self)
			)
			(5
				(agentHuff setMotion: MoveTo 25 111 self)
			)
			(6
				(agentHuff
					illegalBits: 0
					ignoreActors: 1
					setMotion: MoveTo -10 111 self
				)
			)
			(7
				(HandsOn)
				(proc11_2 1)
				(agentHuff dispose:)
			)
		)
	)
)

(instance eSitAS of Script

	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(if (ego inRect: 203 172 227 178)
					(ego setMotion: MoveTo 210 172 self)
				else
					(= cycles 1)
				)
			)
			(1
				(ego
					ignoreActors:
					ignoreControl: cWHITE
					setMotion: MoveTo 241 160 self
				)
			)
			(2
				(ego
					setMotion: MoveTo (eChair x?) (- (eChair y?) 3) self
				)
			)
			(3
				(ego
					view: 11
					loop: 7
					posn: (eChair x?) (- (eChair y?) 1)
					setAvoider: 0
					heading: 90
					setCycle: EndLoop self
				)
			)
			(4
				(User canInput: TRUE)
				(= seconds 10)
			)
			(5 (Print 11 15))
		)
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((Said 'sit')
				(Print 11 1)
			)
			((Said 'stand[<up]')
				(ego setScript: eSFAS)
			)
			((Said 'dance')
				(ego setScript: eSFAS 0 1)
			)
			((Said 'buy,order/drink[<babe,stacy][/babe,stacy]')
				(Print 11 16)
			)
			((== state 5)
				(cond 
					((Said 'affirmative')
						(ego setScript: ePUAS)
					)
					((Said 'n')
						(Print 11 17)
						(agentHuff setScript: agentHuffScript)
					)
					(else
						(Print 11 18)
						(event claimed: TRUE)
					)
				)
			)
		)
	)
)

(instance ePUAS of Script

	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego setCycle: BegLoop self)
				(proc11_2 0)
				(SolvePuzzle tahiti 413 64 7)
				(agentHuff
					setPri: (- (agentHuff priority?) 1)
					setLoop: 1
					setCycle: BegLoop self
				)
			)
			(1
				(ego
					view: 206
					posn: (eChair x?) (- (eChair y?) 3)
					setLoop: -1
					setCycle: Walk
					ignoreActors: 0
					ignoreControl: cWHITE
					setMotion: MoveTo 260 150 self
				)
				(agentHuff
					view: 212
					setCycle: Walk
					setLoop: -1
					z: 0
					setPri: 12
					ignoreActors: TRUE
					illegalBits: 0
				)
			)
			(2
				(ego setMotion: MoveTo 238 129 self)
				(agentHuff setMotion: Follow ego setPri: -1)
			)
			(3
				(ego setMotion: MoveTo 130 121 self)
			)
			(4
				(ego setMotion: MoveTo 25 111 self)
			)
			(5 (curRoom newRoom: 13))
		)
	)
)

(instance eSFAS of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (ego setCycle: BegLoop self))
			(1
				(if (not register)
					(HandsOn)
				)
				(ego
					view: 206
					loop: 0
					setLoop: -1
					setCycle: Walk
					ignoreActors: 0
					observeControl: cWHITE
					setScript:
					(switch register
						(0 0)
						(1 wTDS)
					)
				)
			)
		)
	)
)

(instance gADS of Script

	(method (changeState newState)
		(switch (= state newState)
			(0
				(Print 11 19)
				(proc11_2 0)
				(HandsOff)
				(if (ego inRect: 206 150 246 168)
					(ego setMotion: MoveTo 257 152 self)
				else
					(= cycles 1)
				)
			)
			(1
				(ego setMotion: MoveTo 271 129 self)
			)
			(2
				(Print 11 20)
				(QueScript bartender gCS self)
			)
			(3
				(ego view: 207 setLoop: 0 setMotion: MoveTo 271 165 self)
			)
			(4
				(Print 11 21)
				(Print 11 22)
				(ego cycleSpeed: 4 loop: 1 setCycle: EndLoop self)
			)
			(5
				(cV setPri: addToPic:)
				(tahiti flags: (| (tahiti flags?) $0004))
				(agentHuff
					cycleSpeed: 3
					ignoreControl: cWHITE
					loop: 8
					setCycle: EndLoop self
				)
			)
			(6
				(ego loop: 2 setCycle: EndLoop self)
			)
			(7 (agentHuff setCycle: BegLoop self))
			(8
				(agentHuff loop: 9 setCel: 0 stopUpd:)
				(ego loop: 3 setCycle: EndLoop self)
			)
			(9
				(ego
					view: 206
					setLoop: -1
					loop: 2
					setCycle: Walk
					cycleSpeed: 0
					setScript: 0
				)
				(agentHuff cycleSpeed: 0)
				(HandsOn)
				(proc11_2 1)
			)
		)
	)
)

(instance gCS of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(client
					setCycle: Walk
					setLoop: 6
					setMotion: MoveTo 207 60 self
				)
			)
			(1
				(client setLoop: 9 setMotion: MoveTo 278 90 self)
			)
			(2
				(ego cue:)
				(client
					setLoop: 0
					setMotion: MoveTo 207 60 self
					setCycle: Walk
				)
			)
			(3
				(client setLoop: 5 setMotion: MoveTo 193 66 self)
			)
			(4
				(client setLoop: 1 setCel: 0)
				(self dispose:)
			)
		)
	)
)

(instance aCup of View
	(properties
		y 152
		x 283
		view 11
		cel 4
		priority 15
		signal (| ignrAct staticView ignrAct stopUpdOn)
	)
)

(instance cV of View
	(properties
		y 152
		x 273
		view 11
		cel 5
		priority 15
		signal (| ignrAct staticView ignrAct stopUpdOn)
	)
)

(instance wTDS of Script

	(method (changeState newState)
		(switch (= state newState)
			(0
				(proc11_2 0)
				(agentHuff
					setLoop: 1
					ignoreActors:
					ignoreControl: cWHITE
					setPri: -1
					setCycle: BegLoop self
				)
			)
			(1
				(HandsOff)
				(if (== (ego view?) 11)
					(ego setCycle: BegLoop self)
				else
					(= cycles 1)
				)
			)
			(2
				(ego
					view: 206
					setCycle: Walk
					setMotion: MoveTo 263 158 self
				)
				(agentHuff
					view: 212
					setCycle: Walk
					setLoop: -1
					z: 0
					setMotion: MoveTo 267 158 self
				)
			)
			(3 0)
			(4
				(ego setMotion: MoveTo 151 158 self)
				(barSong client: 0 fade:)
				(agentHuff
					ignoreActors:
					observeControl: cWHITE
					setMotion: Follow ego
				)
			)
			(5
				(ego setMotion: MoveTo 134 167 self)
				(tahiti flags: (| (tahiti flags?) $1000))
			)
			(6 (curRoom newRoom: 199))
		)
	)
)

(instance aSS of Script

	(method (changeState newState)
		(switch (= state newState)
			(0
				(agentHuff ignoreActors: TRUE setMotion: MoveTo 179 159 self)
			)
			(1
				(agentHuff setMotion: MoveTo 238 159 self)
			)
			(2
				(agentHuff
					ignoreControl: cWHITE
					setMotion: MoveTo 288 154 self
				)
			)
			(3
				(agentHuff
					view: 211
					setLoop: 1
					setCel: 0
					posn: 288 169 15
					setCycle: EndLoop self
				)
			)
			(4
				(agentHuff
					setScript: aS
					observeControl: cWHITE
					setPri: 15
					stopUpd:
				)
				(proc11_2 1)
			)
		)
	)
)

(instance lush of Actor
	(properties
		y 98
		x 145
		z 15
		view 511
		loop 4
		signal stopUpdOn
		illegalBits $0000
	)
	
	(method (doit)
		(super doit:)
		(if
			(and
				(< loop 7)
				(not (cOS client?))
				(< (cOS state?) 1)
				(ego inRect: 129 90 177 115)
				(== (ego view?) 206)
				local3
			)
			(ChangeScriptState self cOS)
		)
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((Said 'address[/babe,babe,lisa]')
				(cond 
					((> (ego distanceTo: self) 49)
						(event claimed: FALSE)
					)
					((> (lDS state?) 8)
						(Print 11 23)
					)
					((== (ego view?) 206)
						(self cue:)
						(if (not (cOS client?))
							(ChangeScriptState self cOS)
						)
					)
					(else (localproc_304e 611 52 55))
				)
			)
			(
				(or
					(Said 'buy,order/drink,(tai<mai)/babe,lisa')
					(Said 'buy,order/drink,(tai<mai)<babe,lisa')
				)
				(++ local0)
				(cond 
					((and (bartender script?) ((bartender script?) next:))
						(Print 11 24)
					)
					((& (tahiti flags?) $0002)
						(Print 11 25)
					)
					((> (ego distanceTo: self) 50)
						(NotClose)
					)
					(else
						(QueScript bartender bDS 0 lush)
					)
				)
			)
			((> (lDS state?) 8))
			((Said 'look[<at][/babe,lisa]')
				(if (== (ego view?) 511)
					(localproc_304e 611 50 51)
				else
					(localproc_304e 611 48 49)
				)
			)
			((or (> (ego distanceTo: self) 60) (< x 10)))
			((Said 'ask,get/name[<babe]')
				(localproc_304e 611 45 47)
			)
			((or (Said 'dance') (Said '/babe,babe,lisa'))
				(localproc_304e 611 56 63)
			)
		)
	)
)

(instance cOS of Script
	(properties)
	
	(method (doit)
		(if (not (ego inRect: 129 90 177 115))
			(= start state)
			(self dispose:)
		)
		(super doit: &rest)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(Print 11 26)
				(Print 11 27)
				(= seconds 10)
			)
			(1
				(Print 11 28)
				(= seconds 10)
			)
			(2
				(Print 11 29)
				(= seconds 10)
			)
			(3 (self init:))
		)
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((Said 'address[/babe,lisa]')
				(self cue:)
			)
		)
	)
)

(instance lDS of Script

	(method (changeState newState)
		(switch (= state newState)
			(0
				(++ register)
				(lush loop: 5 setCycle: EndLoop)
				(= seconds 2)
			)
			(1
				(lush setCycle: BegLoop)
				(= seconds 5)
			)
			(2
				(if (> register (Random 2 4))
					(= cycles 2)
				else
					(self init:)
				)
			)
			(3 (lush setCycle: EndLoop self))
			(4
				(lush loop: 6 cel: 0)
				(= seconds 5)
			)
			(5
				(lush loop: 5 setCycle: BegLoop self)
			)
			(6
				(lush loop: 6 setCycle: EndLoop self)
			)
			(7
				(if
					(or
						(== (bartender script?) bDS)
						(!= (curRoom curPic?) 11)
					)
					(self init:)
				else
					(bB init:)
					(QueScript bartender bDS 0 lush)
				)
			)
			(8
				(++ local0)
				(bB dispose:)
				(lush loop: 5 setCycle: BegLoop)
				(Animate (cast elements?) 0)
				(if
					(and
						(>= local0 4)
						(not (& (tahiti flags?) $0010))
						(== (ego view?) 511)
					)
					(tahiti flags: (| (tahiti flags?) $0010))
					(= cycles 2)
				else
					(= register 0)
					(self init: client)
				)
			)
			(9
				(if (>= local1 4)
					(ego setScript: eTDS)
				)
				(cOS dispose:)
				(tahiti flags: (| (tahiti flags?) $0002))
				(= register (User canInput?))
				(User canInput: FALSE)
				(lush
					ignoreActors: TRUE
					illegalBits: 0
					posn: 145 106 0
					loop: 7
					setCycle: EndLoop self
				)
				(lAV dispose:)
			)
			(10
				(stars init: setCycle: ForwardCounter 20 self)
				((= local4 (ScriptID 359)) init:)
			)
			(11
				(User canInput: register)
				(stars dispose:)
			)
		)
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((Said 'help[/babe,lisa]')
				(if (and (>= local1 4) (OneOf state 9 10 11))
					(ego setScript: eTDS)
				else
					(Print 11 30)
				)
			)
			(
			(or (Said '(get<up),stand[<up]') (Said 'exit/stool'))
				(cond 
					((!= (ego view?) 511)
						(event claimed: FALSE)
					)
					((& (tahiti flags?) $0002)
						(Print 11 31)
					)
					(local1
						(Print 11 32)
						(++ local0)
						(HandsOff)
						(QueScript bartender bDS 0 ego)
					)
					((== (ego view?) 206)
						(Print 11 33)
					)
					(else (ego setScript: eSStandS))
				)
			)
			((Said 'look[<at][/babe,lisa]')
				(if (OneOf state 9 10 11)
					(Print 11 34)
				else
					(Print 11 35)
				)
			)
		)
	)
)

(instance stars of Prop
	(properties
		y 96
		x 130
		view 611
		loop 1
	)
)

(instance bDS of Script

	(method (changeState newState)
		(switch (= state newState)
			(0
				(client
					setLoop: 5
					illegalBits: 0
					setCycle: Walk
					setMotion: MoveTo
						(if (== register lush) (+ 15 (lush x?)) else (ego x?))
						(bartender y?)
						self
				)
			)
			(1
				(client setLoop: 1 setCycle: EndLoop self)
			)
			(2
				(client setLoop: 2 setCycle: Forward)
				(= cycles 8)
			)
			(3
				(client setLoop: 3 setCycle: EndLoop self)
			)
			(4
				(client setLoop: 3 setCycle: BegLoop self)
			)
			(5
				(cond 
					((== register ego) (++ local1) (ego setScript: eDS))
					((!= (lush script?) lDS) (lush setScript: lDS))
					(else (lush cue:))
				)
				(client loop: 1 setCycle: EndLoop self)
			)
			(6
				(client
					setLoop: 6
					setCycle: Walk
					setMotion: MoveTo 193 66 self
				)
			)
			(7
				(client setLoop: 1 cel: 0 stopUpd:)
				(self dispose:)
			)
		)
	)
)

(instance eDS of Script

	(method (dispose)
		(ego cycleSpeed: 0)
		(super dispose:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOn)
				(User canControl: 0)
				(if (>= local1 4)
					(ego setScript: eTDS)
				else
					(ego view: 511 cycleSpeed: 2 setLoop: 3 setCycle: EndLoop)
					(++ register)
					(= seconds 2)
				)
			)
			(1
				(ego setCycle: BegLoop)
				(= seconds (Random 5 10))
			)
			(2
				(if (< register 5) (self init:))
			)
		)
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((Said 'drink')
				(if (>= register 5)
					(Print 11 36)
				else
					(self init:)
				)
			)
		)
	)
)

(instance eTDS of Script

	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego view: 611 loop: 3 setCycle: EndLoop self)
			)
			(1 (EgoDead 907 1 0 11 37))
		)
	)
)

(instance bB of View
	(properties
		y 66
		x 162
		view 611
		loop 2
		priority 15
		signal (| staticView fixPriOn stopUpdOn)
	)
)

(instance lAV of View
	(properties
		y 106
		x 145
		view 511
		loop 2
		cel 3
	)
)

(instance eSSS of Script

	(method (changeState newState)
		(switch (= state newState)
			(0
				(proc11_2 0)
				(HandsOff)
				(if (< (ego x?) (register x?))
					(ego
						ignoreControl: cWHITE
						ignoreActors:
						setMotion: MoveTo (- (register x?) 9) (+ (register y?) 3) self
					)
				else
					(ego
						ignoreControl: cWHITE
						setMotion: MoveTo (+ (register x?) 9) (+ (register y?) 3) self
					)
				)
			)
			(1
				(ego
					view: 511
					loop: (if (< (ego x?) (register x?)) 0 else 1)
					posn: (register x?) (register y?)
					heading: 0
					setCycle: EndLoop self
				)
			)
			(2
				(if (cOS client?) (cOS dispose:))
				(= local3 0)
				(proc11_2 1)
				(HandsOn)
				(User canControl: 0)
			)
		)
	)
)

(instance eSStandS of Script

	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(proc11_2 0)
				(ego loop: 0 setCycle: BegLoop self)
			)
			(1
				(register show:)
				(if (not (IsObject local4)) (HandsOn))
				(ego
					view: 206
					loop: 3
					heading: 0
					setLoop: -1
					posn: (- (ego x?) 9) (+ (ego y?) 3)
					setCycle: Walk
					observeControl: cWHITE
					ignoreActors: 0
					setScript: (if (>= local1 4) eTDS else 0)
				)
				(proc11_2 1)
			)
		)
	)
)

(instance bartender of Actor
	(properties
		y 66
		x 193
		view 411
		loop 1
		signal (| ignrAct stopUpdOn)
		illegalBits $0000
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((Said 'look[<at][/bartender]')
				(Print 611
					(+ 0 (Random 0 1) (if (Random 0 9) 2 else 0))
				)
			)
			(
				(or
					(Said
						'buy,order/((drink,beer,beer,cocktail[<!*]),(mai<tai))[/!*]'
					)
					(Said 'ask/bartender/drink,beer,beer,cocktail,(mai<tai)')
				)
				(if (== (ego view?) 511)
					(HandsOff)
					(QueScript self (Clone bDS) 0 ego)
				else
					(Print 11 38)
				)
			)
			((> (ego distanceTo: self) 120))
			((Said 'ask,get/name[<bartender]')
				(Print 11 39)
			)
			((Said 'address/bartender')
				(localproc_304e 611 4 8)
			)
			((or (Said 'pay/man,bartender') (Said 'pay/tab'))
				(localproc_304e 611 9 10)
			)
		)
	)
)

(instance mFF of RPicView
	(properties
		y 179
		x 233
		view 11
		loop 9
		priority 15
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((Said '[/babe]>')
				(cond 
					((Said 'look[<at]')
						(Print 11 40)
					)
					((> (ego distanceTo: self) 40))
					((Said 'address')
						(Print 11 41)
					)
				)
			)
		)
	)
)

(instance wife of Extra
	(properties
		y 160
		x 230
		z 10
		view 11
		loop 3
		priority 10
		signal (| anExtra fixPriOn)
		cycleType 2
		hesitation 15
		minPause 55
		maxPause 200
		minCycles 1
		maxCycles 1
	)
	
	(method (handleEvent event)
		(wF handleEvent: event)
	)
)

(instance wF of RFeature
	(properties
		y 144
		x 232
		z 15
		heading 270
		nsTop 118
		nsLeft 227
		nsBottom 140
		nsRight 238
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((Said '[/babe]>')
				(cond 
					((Said 'look[<at]')
						(if (and (== (husband view?) 11) (== (husband loop?) 5))
							(localproc_304e 611 66 67)
						else
							(localproc_304e 611 64 65)
						)
					)
					((> (ego distanceTo: wife) 49))
					((Said 'address')
						(localproc_304e 611 68 70)
					)
					((or (Said 'dance') (Said 'ask/*/dance'))
						(localproc_304e 611 71 76)
					)
					((Said 'kiss,fuck,suck')
						(localproc_304e 611 77 79)
					)
					((Said 'buy,order[/babe]/drink')
						(Print 11 42)
					)
				)
			)
			((Said 'ask/babe,name/name[<babe]')
				(Print 11 43)
			)
			((or (Said 'dance,ask[/babe][/dance]') (Said '/babe'))
				(localproc_304e 611 71 76)
			)
		)
		(cond 
			(
				(or
					(not (event claimed?))
					(> (ego distanceTo: wife) 49)
					(!= (ego view?) 206)
					(& (tahiti flags?) $0010)
				)
				0
			)
			((not (husband script?))
				(husband setScript: hS)
			)
			((== (husband view?) 11)
				(hS cue:)
			)
		)
	)
)

(instance mIBF of RPicView
	(properties
		y 181
		x 176
		view 11
		loop 4
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((Said '[/man]>')
				(cond 
					((Said 'look[<at]')
						(Print 11 44)
					)
					((> (ego distanceTo: self) 40))
					((Said 'address')
						(localproc_304e 611 95 107)
					)
				)
			)
		)
	)
)

(instance lABTE of Extra
	(properties
		y 120
		x 225
		view 511
		loop 8
		cycleType 2
		hesitation 15
		minPause 80
		maxPause 200
		minCycles 1
		maxCycles 1
	)
	
	(method (handleEvent event)
		(lABF handleEvent: event)
	)
)

(instance lABF of RFeature
	(properties
		y 98
		x 226
		heading 90
		nsTop 78
		nsLeft 218
		nsBottom 118
		nsRight 234
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((Said 'look[<at][/man,local]')
				(Print 11 45)
			)
			((Said 'address[/man,local]')
				(localproc_304e 611 95 107)
			)
		)
	)
)

(instance pP of PicView
	(properties
		y 54
		x 95
		view 11
	)
)

(instance tP of PicView
	(properties
		y 155
		x 205
		view 11
		loop 1
	)
)

(instance sV of View
	(properties
		y 106
		x 169
		heading 180
		view 11
		loop 2
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((> (ego distanceTo: self) 60))
			((Said 'sit>')
				(if (== (ego view?) 206)
					(event claimed: 1)
					(ego setScript: eSSS 0 self)
				)
			)
			((Said 'stand,(get<up)')
				(cond 
					((== (ego view?) 206) (Print 11 46))
					(
					(and (& (tahiti flags?) $0002) (cast contains: lush)) (event claimed: 0))
					((and local0 local1 (cast contains: lush)) (event claimed: 0) (eDS handleEvent: event))
					(else (ego setScript: eSStandS 0 self))
				)
			)
		)
	)
)

(instance lSV of View
	(properties
		y 106
		x 145
		heading 180
		view 11
		loop 2
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((> (ego distanceTo: self) 60))
			((Said 'sit>')
				(if (== (ego view?) 206)
					(event claimed: TRUE)
					(ego setScript: eSSS 0 self)
				)
			)
			((Said 'stand')
				(if (== (ego x?) 145)
					(ego setScript: eSStandS 0 self)
				else
					(event claimed: FALSE)
				)
			)
		)
	)
)

(instance hCPV of PicView
	(properties
		y 146
		x 169
		heading 90
		view 11
		loop 6
		priority 10
		signal (| ignrAct fixPriOn)
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((> (ego distanceTo: self) 40))
			((Said 'sit')
				(if (== (husband view?) 11)
					(localproc_304e 611 92 94)
				else
					(localproc_304e 611 80 83)
				)
			)
		)
	)
)

(instance cP of PicView
	(properties
		y 167
		x 295
		view 211
		loop 2
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((> (ego distanceTo: self) 30)
				(event claimed: FALSE)
			)
			((cast contains: agentHuff))
			((Said 'sit')
				(Print 11 10)
			)
		)
	)
)

(instance lAB of Prop
	(properties
		y 98
		x 98
		z 15
		view 611
		signal stopUpdOn
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((Said 'look[<at][/babe]')
				(Print 11 47)
			)
			((!= (ego view?) 206))
			((> (ego distanceTo: self) 40))
			(
				(and
					(< (ego x?) x)
					(or
						(Said '/babe,babe')
						(Said 'address,dance[/babe,babe]')
						(Said 'buy,order/drink[<babe][/babe]')
					)
				)
				(self setCycle: EndLoop self)
			)
		)
	)
	
	(method (cue)
		(self cel: 0 stopUpd:)
	)
)

(instance lABAP of PicView
	(properties
		y 106
		x 98
		view 511
		loop 2
	)
)

(instance mAB of RPicView
	(properties
		y 105
		x 121
		view 511
		loop 2
		cel 1
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((Said 'look[<at][/man]')
				(Print 11 48)
			)
			((Said 'hit/man')
				(Print 11 49)
			)
			((> (ego distanceTo: self) 40))
			((Said 'address[/man]')
				(localproc_304e 611 95 107)
			)
		)
	)
)

(instance cPPV of RPicView
	(properties
		y 179
		x 206
		view 811
		loop 6
		priority 15
		signal ignrAct
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((Said '[/flower,centerpiece]>')
				(cond 
					((Said 'look[<at]')
						(Print 11 50)
					)
					((> (ego distanceTo: self) 40))
					((Said 'smell')
						(Print 11 51)
					)
				)
			)
		)
	)
)

(instance mirror of RFeature
	(properties
		y 63
		x 260
		nsTop 46
		nsLeft 234
		nsBottom 81
		nsRight 287
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((Said 'look[<at,in][/mirror]')
				(Print 11 52)
			)
			((or (Said 'break/mirror') (Said 'throw/*/mirror'))
				(BadIdea)
			)
		)
	)
)

(instance eCC of Code

	(method (doit param1 param2)
		(if (param1 isKindOf: Extra)
			(if param2
				(param1 startExtra:)
			else
				(param1 stopExtra:)
			)
		)
	)
)
