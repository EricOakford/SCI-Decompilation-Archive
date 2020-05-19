;;; Sierra Script 1.0 - (do not remove this comment)
(script# 76)
(include game.sh)
(use Main)
(use Intrface)
(use LoadMany)
(use Chase)
(use RFeature)
(use Avoider)
(use Sound)
(use Motion)
(use Game)
(use User)
(use Actor)
(use System)

(public
	rm76 0
)

(local
	egoInvisible
)
(instance rm76 of Room
	(properties
		picture 76
		east 75
		west 77
	)
	
	(method (init)
		(LoadMany VIEW 55 276)
		(if (ego has: iFourLeafClover)
			(Load VIEW 170)
		)
		(self style:
		(switch prevRoomNum
			(west WIPERIGHT)
			(east WIPELEFT)
		))
		(super init:)
		(ego init:)
		(NormalEgo)
		(addToPics add: door eachElementDo: #init doit:)
		(statue init: stopUpd:)
		(switch prevRoomNum
			(west
				(ego posn: 3 (proc0_17 187 (ego x?) 154))
			)
			(else 
				(ego posn: 279 118)
				(if (not (Btst fLepsGone))
					(LoadMany SOUND 47 42 27)
					(curRoom setScript: getHim_)
				)
			)
		)
		(= egoInvisible (Btst fInvisible))
	)
	
	(method (doit &tmp nRoom)
		(cond 
			((and (== script getHim_) (Btst fInvisible) (not egoInvisible))
				(= egoInvisible TRUE)
				(Print 76 0)
			)
			((and script (!= script getHim_))
				(script doit:)
			)
			(
				(= nRoom
					(switch ((User alterEgo?) edgeHit?)
						(NORTH north)
						(EAST east)
						(SOUTH south)
						(WEST west)
					)
				)
				(self newRoom: nRoom)
			)
			(script
				(script doit:)
			)
		)
	)
	
	(method (dispose)
		(DisposeScript AVOIDER)
		(DisposeScript CHASE)
		(super dispose:)
	)
	
	(method (handleEvent event)
		(cond 
			((event claimed?) (return))
			((super handleEvent: event) (return))
			((Said 'eat,consume/mushroom')
				(if (ego has: iMushroom)
					(if (curRoom script?)
						(Print 76 1)
					else
						(event claimed: FALSE)
					)
				else
					(event claimed: FALSE)
				)
			)
			(
				(or
					(Said 'look,check/lep,guard')
					(MousedOn g1 event shiftDown)
					(MousedOn g2 event shiftDown)
				)
				(if (Btst fLepsGone)
					(Print 76 2)
				else
					(Print 76 3)
				)
			)
			((or (Said 'look,check/door') (MousedOn door event shiftDown))
				(Print 76 4)
			)
			((Said 'look,check>')
				(if (Said '[<at,around][/room,cave]')
					(Print 76 5)
					(if (not (Btst fLepsGone))
						(Print 76 6)
					)
				)
			)
			((Said 'close,shut/door') (Print 76 7))
			(
				(or
					(Said 'talk,speak/lep,guard')
					(Said 'hello')
					(Said 'say/hello')
				)
				(if (Btst fLepsGone)
					(Print 76 8)
				else
					(Print 76 9)
				)
			)
			((Said 'kill/lep,guard')
				(if (Btst fLepsGone)
					(Print 76 2)
				else
					(Print 76 10)
				)
			)
			(
				(and
					(== (event type?) mouseDown)
					(& (event modifiers?) shiftDown)
					(== (OnControl PMAP (event x?) (event y?)) allEvents)
				)
				(Print 76 11)
			)
			((Said '/stalactite,stalactite>')
				(cond 
					((Said 'take,bend')
						(Print 76 12)
					)
					((Said 'look,check')
						(Print 76 11)
					)
				)
			)
			((and (curRoom script?) (Said 'use/shot'))
				(if (Random 0 1)
					(Print 76 13)
				else
					(Print 76 14)
				)
			)
		)
	)
)

(instance getHim_ of Script
	(properties
		name "getHim!"
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(User canControl: FALSE)
				((ScriptID 0 21) number: 47 loop: 1 play:)
				(if (Btst fInvisible)
					(Print 76 0)
				)
				(ego loop: 1 setMotion: 0)
				(g1
					setLoop: 2
					setCycle: Walk
					setMotion: MoveTo 149 119
					init:
				)
				(g2
					setLoop: 2
					setCycle: Walk
					setMotion: MoveTo 200 148 self
					init:
				)
			)
			(1
				(if (ego has: iFourLeafClover)
					(Print 76 15)
				else
					(Print 76 16)
				)
				(User canControl: TRUE)
				(= seconds 2)
			)
			(2
				(g1
					setCycle: Walk
					setAvoider: Avoider
					setMotion: Chase ego (if (ego has: 6) 30 else 20) self
				)
				(g2
					setCycle: Walk
					setAvoider: Avoider
					setMotion: Chase ego (if (ego has: 6) 30 else 20) self
				)
			)
			(3
				(if (ego has: iFourLeafClover)
					(Print 76 17)
					(g1 stopUpd: setMotion: 0)
					(g2 stopUpd: setMotion: 0)
					(curRoom setScript: poofGuards)
				else
					((ScriptID 0 21) stop:)
					(EgoDead 76 18)
				)
			)
		)
	)
	
	(method (handleEvent event)
		(cond 
			((event claimed?) (return))
			((super handleEvent: event) (return))
			((Said 'fiddle,play/fiddle,jig')
				(cond 
					((Btst fInvisible)
						(Print 76 19)
					)
					((not (ego has: iFiddle))
						(DontHave)
					)
					(else
						(curRoom setScript: fiddler)
					)
				)
			)
		)
	)
)

(instance fiddler of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				((ScriptID 0 21) number: 42 loop: 1 play: self)
				(ego view: 55 cycleSpeed: 1 setCycle: Forward)
				(g1 setMotion: 0)
				(g2 setMotion: 0)
				(= seconds 3)
			)
			(1
				(g1 loop: 3 setCycle: Forward)
				(g2 loop: 4 setCycle: Forward)
				(= seconds 5)
			)
			(2
				(Print 76 20)
				(= seconds 4)
			)
			(3
				(Print 76 21)
				(self setScript: poofGuards self)
			)
			(4
				(ego setCycle: Forward)
			)
			(5
				(NormalEgo)
				(ego loop: 2)
				(HandsOn)
				(SolvePuzzle fLepsDance 3)
				(self dispose:)
			)
		)
	)
)

(instance poofGuards of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego stopUpd:)
				(poof posn: (g1 x?) (g1 y?) init: setCycle: CycleTo 7 1 self)
				(TempSound number: 27 loop: 1 init: play:)
			)
			(1
				(g1 dispose:)
				(poof setCycle: EndLoop self)
			)
			(2
				(poof
					cel: 0
					posn: (g2 x?) (g2 y?)
					setCycle: CycleTo 7 1 self
				)
				(TempSound number: 27 loop: 1 play:)
			)
			(3
				(g2 dispose:)
				(Bset fLepsGone)
				(poof setCycle: EndLoop self)
			)
			(4
				(poof dispose:)
				(self dispose:)
			)
		)
	)
)

(instance door of RPicView
	(properties
		x 286
		y 109
		view 276
	)
	
	(method (handleEvent)
	)
)

(instance statue of View
	(properties
		x 194
		y 128
		noun '/pillar,beam,clover,pillar'
		sightAngle 360
		longRangeDist 500
		shiftClick verbLook
		view 276
		cel 1
	)
	
	(method (handleEvent event)
		(cond 
			((event claimed?) (return))
			((super handleEvent: event) (return))
			((Said 'look,check/pillar,beam')
				(self doVerb: verbLook)
			)
		)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(verbLook
				(Print 76 22)
			)
		)
	)
)

(instance g1 of Actor
	(properties
		x 113
		y 157
		description {leprechaun}
		view 276
		cel 2
	)
)

(instance g2 of Actor
	(properties
		x 125
		y 183
		description {leprechaun}
		view 276
		cel 2
	)
)

(instance poof of Prop
	(properties
		view 170
		loop 2
	)
	
	(method (doVerb)
	)
)

(instance TempSound of Sound
	(properties
		priority 6
	)
)
