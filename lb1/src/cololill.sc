;;; Sierra Script 1.0 - (do not remove this comment)
(script# 270)
(include sci.sh)
(use Main)
(use Intrface)
(use Motion)
(use Game)
(use User)
(use Actor)
(use System)

(public
	cololill 0
)
(synonyms
	(colonel fellow)
	(lil girl)
)

(local
	local0
	local1
	local2
	local3
	local4
	local5
)
(procedure (localproc_000c)
	(Colonel cycleSpeed: 1 setCycle: Fwd)
	(Print
		&rest
		#at
		15
		10
		#font
		4
		#width
		125
		#mode
		1
		#draw
		#dispose
	)
)

(procedure (localproc_003a)
	(Lillian loop: 1 setCycle: Fwd)
	(LHead loop: 7 setCycle: Fwd)
	(Print
		&rest
		#at
		140
		10
		#font
		4
		#width
		125
		#mode
		1
		#draw
		#dispose
	)
)

(instance cololill of Rgn
	(properties)
	
	(method (init)
		(super init:)
		(Load rsFONT 4)
		(Load rsVIEW 909)
		(LoadMany 143 243 298)
		(= global208 512)
		(= [global377 9] 298)
		(if (== [global368 1] 0) (= [global368 1] 1800))
		(Colonel init: stopUpd:)
		(smoke1
			view: 304
			loop: 2
			cel: 0
			x: 182
			y: 97
			z: 30
			init:
			hide:
		)
		(smoke2
			view: 304
			loop: 3
			cel: 0
			posn: (+ (Colonel x?) 11) (- (Colonel y?) 24)
			setPri: (CoordPri (Colonel y?))
			init:
			hide:
		)
		(Glow
			posn: (+ (Colonel x?) 10) (Colonel y?)
			z: 29
			init:
			hide:
		)
		(if (and (== [global368 1] 1) (& global118 $0002))
			(= global195 512)
			(Colonel setScript: colonelActions)
		else
			(Load rsFONT 41)
			(Load rsVIEW 642 905)
			(LoadMany 132 29 94 95 96)
			(LoadMany 143 406 372)
			(= global208 544)
			(= [global377 5] 372)
			(LHead init:)
			(Lillian init:)
			(Bset 38)
			(self setScript: argue)
		)
	)
	
	(method (doit)
		(super doit:)
	)
	
	(method (dispose)
		(super dispose:)
	)
	
	(method (handleEvent event)
		(super handleEvent: event)
		(if (event claimed?) (return))
		(if (== (event type?) evSAID)
			(cond 
				((Said 'hear/fifi,colonel')
					(if (cast contains: Lillian)
						(Print 270 0)
					else
						(Print 270 1)
					)
				)
				((cast contains: Lillian))
				((Said 'converse,ask,tell')
					(if local4
						(Print 270 2)
					else
						(= theTalker 10)
						(Say 1 270 3)
						(++ local4)
					)
				)
				((Said 'deliver/*') (if haveInvItem (Print 270 4) else (DontHave)))
				((Said 'hold/*]') (if haveInvItem (Print 270 5) else (DontHave)))
			)
		)
	)
)

(instance argue of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(cond 
					((not global216) (= state -1))
					((not (& global118 $0002))
						(= global118 (| global118 $0002))
						(self setScript: (ScriptID 406 0))
						(= state -1)
					)
					((self script?) (= state -1))
				)
				(= cycles 1)
			)
			(1
				(User canInput: 0)
				(if
				(== (= local5 (& gCurRoomNum_2 $7fff)) gCurRoomNum_2)
					(if (< gCurRoomNum_2 10)
						(++ gCurRoomNum_2)
					else
						(= gCurRoomNum_2 -32767)
					)
				else
					(switch local5
						(1 (= gCurRoomNum_2 -32766))
						(2 (= gCurRoomNum_2 -32763))
						(5 (= gCurRoomNum_2 -32762))
						(6 (= gCurRoomNum_2 -32760))
						(8 (= gCurRoomNum_2 -32752))
						(10 (= gCurRoomNum_2 -32767))
					)
				)
				(switch local5
					(0 (localproc_003a 270 6))
					(1 (localproc_003a 270 7))
					(2 (localproc_000c 270 8))
					(3 (localproc_000c 270 9))
					(4 (localproc_003a 270 10))
					(5 (localproc_000c 270 11))
					(6 (localproc_003a 270 12))
					(7 (localproc_000c 270 13))
					(8 (localproc_000c 270 14))
					(9 (localproc_003a 270 15))
					(10 (localproc_003a 270 16))
				)
				(= seconds 5)
			)
			(2
				(LHead setCycle: 0)
				(cls)
				(Lillian setCycle: 0)
				(Colonel setCycle: 0)
				(= seconds 3)
				(switch local5
					(0 (localproc_003a 270 17))
					(5 (localproc_000c 270 18))
					(6 (localproc_000c 270 19))
					(8 (localproc_003a 270 20))
					(9 (localproc_000c 270 21))
					(10 (localproc_000c 270 22))
					(else  (= seconds 1))
				)
			)
			(3
				(cls)
				(LHead setCycle: 0 setScript: headActions)
				(Lillian setCycle: 0 setScript: lillActions)
				(Colonel setScript: colonelActions)
				(User canInput: 1)
				(client setScript: 0)
			)
		)
	)
)

(instance colonelActions of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(smoke2 cel: 0 hide:)
				(Colonel
					view: 304
					loop: 0
					cycleSpeed: 1
					cel: 0
					setCycle: End self
				)
			)
			(1
				(Glow show: loop: 1 cel: 0 setCycle: Fwd)
				(= cycles 18)
			)
			(2
				(Glow hide:)
				(Colonel
					loop: 0
					cel: (- (NumCels Colonel) 1)
					setCycle: Beg self
				)
			)
			(3
				(smoke2 setCycle: Fwd show:)
				(smoke1 show: setCycle: End self)
			)
			(4
				(smoke1 cel: 0 hide:)
				(= cycles 1)
			)
			(5
				(if (< (Random 1 100) 30)
					(= state -1)
				else
					(= state 4)
				)
				(= seconds 5)
			)
		)
	)
)

(instance lillActions of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if (!= (Lillian cel?) 0)
					(Lillian loop: 0 cel: 3 setCycle: Beg self)
				else
					(= cycles 1)
				)
			)
			(1
				(Lillian loop: 0 cel: 0)
				(= seconds (Random 6 15))
			)
			(2
				(Lillian loop: 2 cel: 0 setCycle: End)
				(= seconds (Random 6 15))
			)
			(3
				(if
				(and (== (LHead loop?) 5) (== (LHead cel?) 2))
					(Lillian loop: 3 cel: 0 setCycle: End self)
				else
					(= state 0)
					(= cycles 1)
				)
			)
			(4
				(Lillian loop: 4 cel: 0 setCycle: Fwd)
				(= seconds 3)
			)
			(5
				(Lillian loop: 2 cel: 2 setCycle: Beg self)
				(= state 0)
			)
		)
	)
)

(instance headActions of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= seconds (Random 12 18)))
			(1
				(LHead loop: 5 cel: 0 setCycle: End)
				(= seconds (Random 12 18))
			)
			(2
				(LHead setCycle: Beg)
				(= seconds (Random 12 18))
			)
			(3
				(LHead loop: 6 cel: 0 setCycle: End)
				(= seconds (Random 2 8))
			)
			(4
				(LHead setCycle: Beg)
				(= seconds (Random 12 18))
				(= state 0)
			)
		)
	)
)

(instance Colonel of Prop
	(properties
		y 97
		x 176
		view 312
	)
	
	(method (handleEvent event)
		(if
		(< (ego distanceTo: Colonel) (ego distanceTo: Lillian))
			(= global214 512)
		else
			(= global214 32)
		)
		(if (cast contains: Lillian)
			(cond 
				((Said 'examine/people') (Print 270 23))
				((Said 'examine,converse/person') (Print 270 24))
				((Said 'converse/people') (Print 270 25))
			)
		)
		(cond 
			((Said 'converse/colonel,person')
				(if (cast contains: Lillian)
					(= theTalker 10)
					(switch local1
						(0 (Say 1 270 26))
						(1 (Say 1 270 27))
						(2 (Say 1 270 28))
						(3 (Say 1 270 29))
						(else  (Print 270 30))
					)
					(++ local1)
				else
					(event claimed: 0)
				)
			)
			((Said 'examine/butt') (Bset 13) (Print 270 31))
			((Said 'get/butt') (Print 270 32))
			(
				(or
					(MousedOn self event 3)
					(Said 'examine/colonel,person')
				)
				(if (& global207 $0200)
					(if (cast contains: Lillian)
						(Print 270 23)
					else
						(Print 270 33)
					)
				else
					(= global207 (| global207 $0200))
					(ParseName {colonel})
				)
				(event claimed: 1)
			)
		)
	)
)

(instance smoke1 of Prop
	(properties
		view 304
		loop 2
	)
)

(instance smoke2 of Prop
	(properties
		view 304
		loop 3
	)
)

(instance Lillian of Prop
	(properties
		y 94
		x 221
		view 502
	)
	
	(method (handleEvent event)
		(return
			(cond 
				(
				(and (MousedOn self event 3) (not (& global207 $0020))) (event claimed: 1) (ParseName {lillian}))
				(
					(and
						(& global207 $0020)
						(or (MousedOn self event 3) (Said 'examine/lil'))
					)
					(event claimed: 1)
					(Print 270 23)
				)
				((Said 'converse/lil')
					(= theTalker 6)
					(switch local2
						(0 (Say 1 270 34))
						(1 (Say 1 270 35))
						(2 (Say 1 270 36))
						(3 (Say 1 270 37))
						(else  (Say 1 270 38))
					)
					(++ local2)
				)
			)
		)
	)
)

(instance LHead of Prop
	(properties
		y 94
		x 221
		z 39
		view 502
		loop 7
	)
)

(instance Glow of Prop
	(properties
		view 304
		loop 1
	)
)
