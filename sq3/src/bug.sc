;;; Sierra Script 1.0 - (do not remove this comment)
(script# 511)
(include game.sh)
(use Main)
(use Intrface)
(use Wander)
(use Follow)
(use Chase)
(use Avoider)
(use Sound)
(use Motion)
(use Actor)
(use System)

(public
	bug 0
)

(local
	local0
	bugX
	bugY
	local3
	bugBlockBottom
)
(instance bug of Actor
	(method (init)
		(super init:)
		(self setScript: scorp)
	)
	
	(method (delete)
		(super delete:)
		(DisposeScript 511)
	)
)

(instance zap of Sound)

(instance bugBlock of Block
	(properties)
)

(instance scorp of Script
	(method (init)
		(super init: &rest)
		(Load SOUND 90)
	)
	
	(method (doit)
		(super doit:)
		(if (!= curRoomNum newRoomNum) (return))
		(if (cast contains: bug)
			(if
				(&
					(OnControl
						2
						(- (bug x?) 13)
						(- (bug y?) 1)
						(+ (bug x?) 13)
						(bug y?)
					)
					global591
				)
				(bug posn: bugX (+ bugY 2))
				(if local0
					(bug setAvoider: Avoider setMotion: Chase ego 5 self)
				else
					(bug setAvoider: 0 setMotion: Wander 200)
				)
			)
			(if
				(and
					(== global104 0)
					(== sawScorpion FALSE)
					(< (ego distanceTo: bug) 150)
				)
				(Print 511 0)
				(= sawScorpion TRUE)
			)
			(if (!= (OnControl 1 (bug x?) (bug y?)) 0)
				(= bugX (bug x?))
				(= bugY (bug y?))
			)
			(if (or (< state 2) (== state 6))
				(cond 
					((!= global104 0)
						(if (== local0 1)
							(= local0 0)
							(self changeState: 6)
						)
					)
					((!= local0 1)
						(= state 1)
						(bug
							setAvoider: 0
							setAvoider: Avoider
							setMotion: Chase ego 5 self
						)
						(= local0 1)
					)
				)
			)
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(bug
					view: 71
					posn: (Random 1 300) (Random 160 180)
					illegalBits: -2
					ignoreControl: 4
					setStep: 3 2
					setLoop: -1
					setCycle: Walk
					setAvoider: Avoider
					setMotion: Chase ego 5 self
				)
				(switch curRoomNum
					(42
						(= bugBlockBottom 0)
						(bug ignoreControl: cLMAGENTA cLRED)
					)
					(44
						(= bugBlockBottom 0)
						(bug ignoreControl: cLMAGENTA)
					)
					(45
						(= bugBlockBottom 82)
					)
					(46
						(= bugBlockBottom 80)
						(bug ignoreControl: cLCYAN)
					)
					(47
						(= bugBlockBottom 105)
						(bug ignoreControl: cRED)
					)
					(48
						(= bugBlockBottom 86)
					)
					(50
						(= bugBlockBottom 55)
						(bug ignoreControl: cCYAN)
					)
					(51
						(= bugBlockBottom 87)
						(bug ignoreControl: cCYAN)
					)
					(52
						(= bugBlockBottom 97)
					)
				)
				(bugBlock top: 0 bottom: bugBlockBottom right: 319 left: 0)
				(bug observeBlocks: bugBlock)
				(if (and (not sawScorpion) (== global104 0))
					(Print 511 0)
					(= sawScorpion TRUE)
				)
				(self changeState: 1)
				(= local0 1)
			)
			(2
				(if (!= curRoomNum newRoomNum) (return))
				(if (!= global104 0)
					(= local0 0)
					(self changeState: 6)
					(return)
				)
				(HandsOff)
				(ego setMotion: 0)
				(if (< (bug x?) (ego x?))
					(bug setLoop: 6 cel: 255)
				else
					(bug setLoop: 7 cel: 255)
				)
				(bug
					setCycle: EndLoop self
					setAvoider: 0
					setAvoider: Avoider
					setMotion: Follow ego 1
				)
			)
			(3
				(if (!= global104 0)
					(= local0 0)
					(self changeState: 6)
					(return)
				)
				(HandsOff)
				(ego setMotion: 0)
				(zap number: 90 priority: 10 play:)
				(bug
					setMotion: 0
					setPri: 14
					setLoop: 8
					setAvoider: 0
					cel: 0
				)
				(= cycles 3)
			)
			(4
				(if (!= global104 0)
					(= local0 0)
					(self changeState: 6)
					(return)
				)
				(ego view: 16 setLoop: 0 cel: (& (ego loop?) $0001))
				(bug
					setPri: -1
					setLoop: -1
					setCycle: Walk
					setAvoider: 0
					setMotion: Wander 200
				)
				(RedrawCast)
				(Print 511 1)
				(= seconds 5)
			)
			(5
				(EgoDead 901 0 12 17)
			)
			(6
				(bug
					setLoop: -1
					setCycle: Walk
					setAvoider: 0
					setMotion: Wander 200
				)
				(= local0 0)
			)
		)
	)
)
